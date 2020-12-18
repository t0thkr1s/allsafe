
 <img align="left" width="132" height="132" src="app/src/main/res/mipmap-xxxhdpi/ic_launcher_round.png">

# Allsafe

[![forthebadge](https://forthebadge.com/images/badges/built-for-android.svg)](https://github.com/t0thkr1s/)
[![forthebadge](https://forthebadge.com/images/badges/built-with-love.svg)](https://github.com/t0thkr1s/)

Allsafe is an intentionally vulnerable application that contains various vulnerabilities. Unlike other vulnerable Android apps, this one is less like a CTF and more like a real-life application that uses modern libraries and technologies. Additionally, I have included some Frida based challenges for you to explore. Have fun and happy hacking!

#### Useful Frida Scripts
 
I have my Frida scripts (more like templates) in other repository. I'm sure they might be quite handy for the Frida related tasks. Check it out: https://github.com/t0thkr1s/frida

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

### 4. Arbitrary Code Execution

Loading modules securely with third-party apps are not easy. Write a PoC application and exploit the vulnerability!

###### Resources & HackerOne Reports:

- [Arbitrary Code Execution via Third-Party Package Contexts](https://blog.oversecured.com/Android-arbitrary-code-execution-via-third-party-package-contexts/)

<details>
<summary>Show me how it's done!</summary>
<br>
# TODO
<br><br>
</details>

---

### 5. Secure Flag Bypass

Another Frida-based task. No real vulnerability here, just have fun bypassing the secure flag!

###### Resources & HackerOne Reports:

- [Android FLAG_SECURE Reference](https://developer.android.com/reference/android/view/WindowManager.LayoutParams#FLAG_SECURE)

<details>
<summary>Show me how it's done!</summary>
<br>
# TODO
<br><br>
</details>

---

### 6. Certificate Pinning Bypass

Certificate pinning is implemented using the OkHttp library. You have to bypass it in order to view the traffic with Burp Suite.

###### Resources & HackerOne Reports:

- [Certificate and Public Key Pinning](https://owasp.org/www-community/controls/Certificate_and_Public_Key_Pinning)
- [Coinbase Vulnerabilities](https://hackerone.com/reports/5786)

<details>
<summary>Show me how it's done!</summary>
<br>
# TODO
<br><br>
</details>

---

### 7. Insecure Broadcast Receiver

There's a vulnerable broadcast recevier in the application. Trigger it with the correct data and you're done!

###### Resources & HackerOne Reports:

- [Android Broadcasts Overview](https://developer.android.com/guide/components/broadcasts)
- [ok.ru Broadcast Receiver Exploitation](https://hackerone.com/reports/97295)
- [Bitwarden Vulnerable Broadcast Receiver](https://hackerone.com/reports/289000)

<details>
<summary>Show me how it's done!</summary>
<br>
# TODO
<br><br>
</details>

---

### 8. Deep Link Exploitation

 Similar to the insecure broadcast receiver, you need to provide the right query parameter to complete this task!

###### Resources & HackerOne Reports:

- [Android Deep Linking](https://developer.android.com/training/app-links/deep-linking)
- [Grab Insecure Deep Link](https://hackerone.com/reports/401793)
- [Periscope Deep Link CSRF](https://hackerone.com/reports/583987)

<details>
<summary>Show me how it's done!</summary>
<br>
# TODO
<br><br>
</details>

---

### Contribute

Noticed a bug? Have a suggestion? Feel free to open an issue or create a pull request!

### Support

If this project was valuable to you or helped you in any way, please consider making a small amount of donation via the following cryptocurrencies. Giving a star on the project also helps a lot. Thanks!

**Bitcoin Address**
⟹ *bc1qd44kvj6zatjgn27n45uxd3nprzt6rm9x9g2yc8*

**Ethereum Address**
⟹ *0x1835a58E866a668C48Ee63d32432C7Fe28aF54b4*

### Disclaimer

> This tool is only for testing and academic purposes and can only be used where strict consent has been given. Do not use it for illegal purposes! It is the end user’s responsibility to obey all applicable local, state and federal laws. Developers assume no liability and are not responsible for any misuse or damage caused by this tool and software.

## License

This project is licensed under the GPLv3 License - see the [LICENSE](LICENSE) file for details
