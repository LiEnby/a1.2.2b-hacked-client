# a1.2.2b-hacked-client
A "Hacked Client" for Minecraft Alpha 1.2.2b


To build: 
1) Download MCP 2.2a  https://minecraft.gamepedia.com/Programs_and_editors/Mod_Coder_Pack
2) DragnDrop a1.2.2b.jar into jars/bin folder along with natives and lwgl.jar and such libs
3) Rename a1.2.2b.jar to minecraft.jar
4) Install JDK 1.6_17 & Add to PATH
5) Run Decompile.bat
6) Copy patched source files into Sources/
7) Run Recompile.bat
8)
in conf/client_obfuscation.txt add the following
```
Entity
EntityLiving
EntityPlayer
EntityPlayerSP
GuiChat
GuiIngame
Minecraft
Block
```
9) Run reobfs.bat

When installed type .help in chat for info on commands
