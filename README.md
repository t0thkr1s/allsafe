# Allsafe

[![forthebadge](https://forthebadge.com/images/badges/built-for-android.svg)](https://github.com/t0thkr1s/)
[![forthebadge](https://forthebadge.com/images/badges/built-with-love.svg)](https://github.com/t0thkr1s/)

Allsafe is an intentionally vulnerable application that contains various vulnerabilities. Additionally, there are some Frida based challenges for you to explore.

## Tasks / Vulnerabilities

### 1. Insecure Logging

Simple information disclosure vulnerability. Use the `logcat` command-line tool to discover sensitive information.

###### Resources & HackerOne Reports:

- [Logcat Tool](https://developer.android.com/studio/command-line/logcat)
- [Coinbase OAuth Response Code Leak](https://hackerone.com/reports/5314)

<details>
<summary>Show me how it's done!</summary>
<br>
# TODO
<br><br>
</details>

---

### 2. Hardcoded Credentials

Some credentials are left in the code. Your task is to reverse engineer the app and find sensitive information.

###### Resources & HackerOne Reports:

- [Zomato Hardcoded Credentials](https://hackerone.com/reports/246995)
- [8x8 Hardcoded Credentials](https://hackerone.com/reports/412772)
- [Reverb Hardcoded API Secret](https://hackerone.com/reports/351555)

<details>
<summary>Show me how it's done!</summary>
<br>
# TODO
<br><br>
</details>

---

### 3. Root Detection

This is purely for Frida practice. Make the code believe that you device is not rooted!

<details>
<summary>Show me how it's done!</summary>
<br>
https://youtu.be/Gg-3Sw79gEI
<br><br>
</details>

---

### Disclaimer

> This tool is only for testing and academic purposes and can only be used where strict consent has been given. Do not use it for illegal purposes! It is the end user’s responsibility to obey all applicable local, state and federal laws. Developers assume no liability and are not responsible for any misuse or damage caused by this tool and software.

## License

This project is licensed under the GPLv3 License - see the [LICENSE](LICENSE) file for details
