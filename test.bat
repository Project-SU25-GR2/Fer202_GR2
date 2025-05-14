@echo off
setlocal ENABLEDELAYEDEXPANSION

:: === CẤU HÌNH ===
set REPO_URL=https://github.com/Project-SU25-GR2/Fer202_GR2.git
set /p BRANCH_NAME=Nhập tên nhánh muốn push (ví dụ: main, dev): 
set /p COMMIT_MSG=Nhập commit message: 

:: === BẮT ĐẦU ===
echo.
echo --------- PUSH CODE TO GITHUB (Branch: %BRANCH_NAME%) ---------
cd /d %~dp0

echo 1. Khởi tạo Git (nếu chưa có)
git init

echo 2. Kiểm tra và set remote origin
git remote remove origin >nul 2>&1
git remote add origin %REPO_URL%

echo 3. Checkout đúng nhánh
git checkout -B %BRANCH_NAME%

echo 4. Thêm file vào staging
git add .

echo 5. Commit với message: %COMMIT_MSG%
git commit -m "%COMMIT_MSG%"

echo 6. Pull trước khi push (nếu có thay đổi từ server)
git pull origin %BRANCH_NAME% --rebase

echo 7. Push code lên GitHub
git push -u origin %BRANCH_NAME%

echo --------- ĐÃ PUSH XONG LÊN NHÁNH: %BRANCH_NAME% ---------
pause
endlocal
