package org.baekya_be.Service;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@Service
public class PythonService {

    public String executePythonScript(String scriptPath, String argument) {
        try {
            // Python 명령어 및 인자 설정
            ProcessBuilder processBuilder = new ProcessBuilder("python3", scriptPath, argument);
            processBuilder.redirectErrorStream(true); // 에러 스트림을 표준 출력으로 합치기

            // 프로세스 실행
            Process process = processBuilder.start();

            // Python 스크립트 출력 읽기
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            StringBuilder output = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }

            int exitCode = process.waitFor(); // 프로세스 종료 대기
            if (exitCode == 0) {
                return output.toString(); // 성공적으로 실행된 경우 결과 반환
            } else {
                throw new RuntimeException("Python script failed with exit code: " + exitCode);
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to execute Python script", e);
        }
    }
}