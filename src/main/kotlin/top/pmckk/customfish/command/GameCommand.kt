package top.pmckk.customfish.command

import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

object GameCommand {
    fun execute(sender: CommandSender) {
        if (!sender.isOp && !sender.hasPermission("customfishing.game") && sender is Player) {
            sender.sendMessage("§6/customfishing game join GameID §f- 加入比赛")
            sender.sendMessage("§6/customfishing game quit GameID §f- 退出比赛")
            return
        }
        sender.sendMessage("§e钓鱼小游戏帮助菜单：")
        sender.sendMessage("§6/customfishing game start §f- 启动小游戏")
        sender.sendMessage("§6/customfishing game stop §f- 停止小游戏")
    }
}