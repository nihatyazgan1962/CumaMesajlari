@echo off
color 0A
title CUMA MESAJLARI APK Olusturucu
echo ========================================================
echo   CUMA MESAJLARI APK Olusturucu Baslatiliyor...
echo ========================================================
powershell -ExecutionPolicy Bypass -File "%~dp0apk_yap.ps1"
