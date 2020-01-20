# a1.2.2b-hacked-client
A "Hacked Client" for Minecraft Alpha 1.2.2b


To build: 
1) Download MCP 2.2a  https://minecraft.gamepedia.com/Programs_and_editors/Mod_Coder_Pack
2) DragnDrop a1.2.2b.jar into jars/bin folder along with natives and lwgl.jar and such libs
3) Rename a1.2.2b.jar to minecraft.jar
4) Install JDK 1.6_17 & Add to PATH
5) Change line 180 and 181 in conf/methods.csv to sin_00 and cos_00
6) Run Decompile.bat
7) Copy patched source files into Sources/
8) Run Recompile.bat
9)
in conf/client_obfuscation.txt add the following
```
CreativeInventory
Entity
EntityLiving
EntityPlayer
EntityPlayerSP
GuiChat
GuiIngame
Minecraft
RenderGlobal
Block
```
10) Run reobfs.bat

When installed type .help in chat for info on commands
