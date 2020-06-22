
**Bot Specific Information**

Helpbot is a bot focused on providing a large amount of commands to the DiamondFire community. Please note, this bot is intended to be used on the DiamondFire server only.
Commands vary from stats related commands to commands that provide codeblock information.
 
***

**Contribution** 

This bot is coded in **java 11** and there are no current rules in place for contribution. If you want to add/moidfy something you are able to create a **fork** and submit a **pull request**.

**Config File**

This project uses a config file inorder to specify things such as bot token, prefix and database information. When you first boot the bot, it will error and shut down the bot. In the working directory a config file should have been created, use the following format.
```json
{
     "token": "bot token",
     "prefix": "?",
     
     "mc_email": "Minecraft@email.com",
     "mc_password": "MinecraftPassword",
     
     "db_link": "jdbc:db_type://ip:port/schema",
     "db_user": "dbuser",
     "db_password": "dbpassword"
     
   }