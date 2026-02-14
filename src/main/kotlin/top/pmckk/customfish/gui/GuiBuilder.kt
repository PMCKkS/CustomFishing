package top.pmckk.customfish.gui

import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack

/**
 * 公共的界面构建类
 * 之所以做这个因为我觉得每个界面都要重复写很麻烦
 * 所以我觉得直接两个类Holder+Builder完成
 * 其他类调用就行了省事
 * @author PMCKK
 */
class GuiBuilder (private val title: String,private val rows: Int){
    //校验界面是否可行
    init {
        require(rows in 1..6){"界面行数必须是1-6,当前值是: $rows"}
    }
    //创建实例对象并赋值给holder
    private val holder = CustomGuiHolder(title)
    //待构建的物品栏
    private val inventory : Inventory = Bukkit.createInventory(null,rows*9,title)

    //往界面塞物品(按钮)
    fun addButton(
        slot : Int,
        material: Material,
        displayeName: String,
        lore: List<String> = emptyList(),
        clickAction: ((Player) -> Unit)
    ): GuiBuilder{
        //检查槽位是否符合规则
        require(slot in 0 until inventory.size){"当前槽位是:$slot 超过规则了,槽位必须是 0-${inventory.size-1}."}

        val item = ItemStack(material)
        val meta = item.itemMeta ?: return this
        if (lore.isEmpty())meta.lore = lore
        item.itemMeta = meta

        //填充物品到指定位置
        inventory.setItem(slot,item)

        //放置拿去物品
        clickAction?.let { holder.clickActions[slot] = it }
        return this
    }

    //填充GUI
    fun fillBlank(material: Material = Material.WHITE_STAINED_GLASS_PANE): GuiBuilder{
        val filler = ItemStack(material)
        val meta = filler.itemMeta ?: return this
        //这里先留空想起来再填物品显示名称AWA
        meta.setDisplayName(" ")
        filler.itemMeta = meta

        //填充
        for (slot in 0 until inventory.size){
            if (inventory.getItem(slot) == null){
                inventory.setItem(slot,filler)
            }
        }
        return this
    }
    //构建并返回物品栏
    fun build(): Inventory{
        holder.setInventory(inventory)
        return inventory
    }
    //构建并给玩家打开gui
    fun open(player : Player){
        val inv = build()
        player.openInventory(inv)
    }
    //静态工具方法
    companion object{
        fun createItem(material: Material,displayeName: String,lore: List<String> = emptyList()): ItemStack{
            val item = ItemStack(material)
            val meta = item.itemMeta?: return item
            meta.setDisplayName(displayeName)
            if (lore.isNotEmpty()) meta.lore = lore
            item.itemMeta = meta
            return item
        }
    }
}