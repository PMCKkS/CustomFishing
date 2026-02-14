package top.pmckk.customfish.command

import org.bukkit.command.CommandExecutor
import org.bukkit.command.PluginCommand
import org.bukkit.command.TabCompleter
import org.bukkit.plugin.java.JavaPlugin

class CommandManager(private val plugin: JavaPlugin) {

    private val mainCommandName = "customfishing"

    fun registerAllCommands(){
        registerCommand(MainCommand())
    }
    private fun registerCommand(executor: CommandExecutor){
        val pluginCommand: PluginCommand?= plugin.getCommand(mainCommandName)

        if (pluginCommand != null){
            pluginCommand.setExecutor(executor)
            if (executor is TabCompleter){
                pluginCommand.tabCompleter = executor as TabCompleter
            }
            plugin.logger.info("命令 /$mainCommandName 注册成功!")
        }else{
         plugin.logger.severe("命令 /$mainCommandName 注册失败!")
        }
    }
}