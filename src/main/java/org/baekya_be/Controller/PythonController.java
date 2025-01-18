package org.baekya_be.Controller;

import org.baekya_be.Service.PythonService;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

@RestController
@RequestMapping("/api")
public class PythonController {

    private final PythonService pythonService;

    public PythonController(PythonService pythonService) {
        this.pythonService = pythonService;
    }

    @GetMapping("/run-python")
    public String runPythonScript(@RequestParam String argument) {
        try {
            // ClassPathResource를 사용해 경로를 동적으로 가져오기
            File scriptFile = new ClassPathResource("scripts/example.py").getFile();
            String scriptPath = scriptFile.getAbsolutePath();
            return pythonService.executePythonScript(scriptPath, argument);
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }
}