@echo off
setlocal ENABLEDELAYEDEXPANSION

:: === CAU HINH ===
set REPO_URL=https://github.com/Project-SU25-GR2/Fer202_GR2.git
set /p BRANCH_NAME=Nhap ten nhanh muon push (vi du: main, dev): 
set /p COMMIT_MSG=Nhap commit message: 

:: === BAT DAU ===
echo.
echo --------- DAY CODE LEN GITHUB (Nhanh: %BRANCH_NAME%) ---------
cd /d %~dp0

echo 1. Khoi tao Git (neu chua co)
git init

echo 2. Kiem tra va thiet lap remote origin
git remote remove origin >nul 2>&1
git remote add origin %REPO_URL%

echo 3. Chuyen den nhanh dang lam viec
git checkout -B %BRANCH_NAME%

echo 4. Them file vao danh sach commit
git add .

echo 5. Tao commit voi noi dung: %COMMIT_MSG%
git commit -m "%COMMIT_MSG%"

echo 6. Lay code moi nhat tu GitHub (neu co)
git pull origin %BRANCH_NAME% --rebase

echo 7. Day code len GitHub
git push -u origin %BRANCH_NAME%
IF ERRORLEVEL 1 (
    echo.
    echo === LOI: Push bi tu choi vi nhanh tren GitHub da co thay doi ===
    echo => Giai phap: Day code lai bang lenh sau (can than khi su dung):
    echo git push -u origin %BRANCH_NAME% --force
    echo.
) ELSE (
    echo --------- DA DAY CODE LEN NHANH: %BRANCH_NAME% ---------
)

pause
endlocal
