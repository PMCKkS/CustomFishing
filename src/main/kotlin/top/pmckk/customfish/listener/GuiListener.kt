package top.pmckk.customfish.listener

import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import top.pmckk.customfish.gui.CustomGuiHolder

/**
 * 公告界面监听
 * @author PMCKK
 */

class GuiListener : Listener {
    @EventHandler
    fun onGuiClick(e : InventoryClickEvent){
        //过滤非玩家点击,非插件界面
        val player = e.whoClicked as? Player ?: return
        val holder = e.inventory.holder as? CustomGuiHolder ?: return

        //取消默认点击行为放置拿取物品
        e.isCancelled = true

        val slot = e.slot
        val clickAction = holder.clickActions[slot] ?: return

        try {
            clickAction.invoke(player)
        }catch (e: Exception){
            player.sendMessage("§c按钮点击错误：${e.message}")
            e.printStackTrace()
        }
    }
}