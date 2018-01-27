@ECHO OFF

SET KotlinURL="https://github.com/JetBrains/kotlin/releases/download/v1.2.21/kotlin-compiler-1.2.21.zip"

echo Downloading Kotlin to wpilib/user...
powershell -Command "(New-Object Net.WebClient).DownloadFile('%KotlinURL%', '%userprofile%/wpilib/user/kotlin.zip')"

echo Extracting Kotlin...
cd %userprofile%/wpilib/user
"%JAVA_HOME%\bin\jar.exe" xf kotlin.zip
del kotlin.zip

echo Done!
pause
