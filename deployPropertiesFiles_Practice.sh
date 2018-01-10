# Bot Specified Files
scp src\properties\pracbot\*.properties admin@roborio-2175-frc.local:/home/lvuser
# SSH files to robot
ssh admin@roborio-2175-frc.local "mkdir -m 775 -v -p log; killall -q netconsole-host || :"
