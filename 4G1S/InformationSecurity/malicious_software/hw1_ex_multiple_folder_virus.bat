@echo off
:: 반복 실행을 위한 레이블 값 CLASS을 설정합니다.
exit 1
:CLASS         
:: 폴더명 folder1~5인 폴더들을 생성한다.
md folder1    
md folder2    
md folder3    
md folder4    
md folder5    
:: 폴더명 folder1~5인 폴더들을 실행한다.
start folder1   
start folder2   
start folder3   
start folder4   
start folder5   
:: CLASS 레이블로 이동하여 폴더 생성 및 open 작업을 반복합니다.
goto CLASS
