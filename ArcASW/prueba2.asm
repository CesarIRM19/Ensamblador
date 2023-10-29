.386
.model flat,stdcall
.stack 4096
ExitProcess proto,dwExitCode:dword

.data
cal1 DWORD ?
cal2 DWORD ?

.code
main proc
	gdgdhhhdhdh
	mov ebx,7
	mul ebx

	mov cal1, eax
	mov eax, 10
	mov ebx, 5
	mul ebx

	mov cal2, eax
	mov eax,5
	mov ebx,3
	add eax,cal1
	sub eax,cal2
	sub eax, ebx
	mov cal1,eax
	invoke
ExitProcess, 0
main endp
end main
