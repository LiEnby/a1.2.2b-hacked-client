# a1.2.2b-hacked-client
A "Hacked Client" for Minecraft Alpha 1.2.2b


To build: 
1) Download MCP 2.2a  https://minecraft.gamepedia.com/Programs_and_editors/Mod_Coder_Pack
2) DragnDrop 1.2.2b.jar into jars/ folder along with natives and lwgl.jar and such libs
3) Install JDK 1.6_17
4) Run Decompile.bat
5) Copy patched source files into Sources/
6) Run Recompile.bat
7)
in conf/client_obfuscation.txt add the following
```
Entity
EntityLiving
EntityPlayerSP
GuiChat
GuiIngame
Minecraft
```

8) Run reobfs.bat

When installed type .help in chat for info on commands
