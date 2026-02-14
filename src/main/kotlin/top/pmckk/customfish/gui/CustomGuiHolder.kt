package top.pmckk.customfish.gui

import org.bukkit.entity.Player
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.InventoryHolder

/**
 * 这是一个公共的界面Holder类
 * 创建理由看[GuiBuilder]
 * @author PMCKK
 */


class CustomGuiHolder (
    val title : String,
    val clickActions : MutableMap<Int, (Player) -> Unit> = mutableMapOf()
) : InventoryHolder{
    private lateinit var inventory: Inventory

    fun setInventory(inv : Inventory){
        this.inventory = inv
    }
    //重写获取Inventory的方法
    override fun getInventory(): Inventory {
        return this::inventory.get()
    }
}