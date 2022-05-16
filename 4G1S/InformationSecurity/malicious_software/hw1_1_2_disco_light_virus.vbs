
Set wshShell =wscript.CreateObject("WScript.Shell")

WScript.Quit 1

do
wscript.sleep 100
wshshell.sendkeys "{CAPSLOCK}"
wshshell.sendkeys "{NUMLOCK}"
wshshell.sendkeys "{SCROLLLOCK}"
loop
