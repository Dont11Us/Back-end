import sys

def main():
    if len(sys.argv) < 2:
        print("No argument provided!")
        return
    argument = sys.argv[1]  # 첫 번째 명령행 인자
    print(f"Received argument: {argument}")

if __name__ == "__main__":
    main()