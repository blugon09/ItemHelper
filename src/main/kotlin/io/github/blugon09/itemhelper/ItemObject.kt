package io.github.blugon09.itemhelper

import com.destroystokyo.paper.Namespaced
import net.kyori.adventure.text.Component
import org.bukkit.Material
import org.bukkit.attribute.Attribute
import org.bukkit.attribute.AttributeModifier
import org.bukkit.enchantments.Enchantment
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack

class ItemObject {
    var type = Material.AIR
    var amount = 1
    var displayName = ""
    var lore = arrayListOf<String>()
    val enchantment = HashMap<Enchantment, Int>()
    var customModelData = 0
    val itemFlag = arrayListOf<ItemFlag>()
    var unbreakable = false
    var attribute = HashMap<Attribute, AttributeModifier>()
    var canPlace = arrayListOf<Material>()
    var canDestroy = arrayListOf<Material>()
    var damage = 0
    val maxDuration = this.type.maxDurability
    val rarity = this.type.itemRarity



    //===================<Create>===================
    constructor(type: Material = Material.AIR) {
        this.type = type
    }
    constructor(type: Material = Material.AIR, amount: Int = 1) {
        this.type = type
        this.amount = amount
    }
    constructor(type: Material = Material.AIR, amount: Int = 1, displayName: String = "") {
        this.type = type
        this.amount = amount
        this.displayName = displayName
    }
    constructor(type: Material = Material.AIR, amount: Int = 1, displayName: String = "", lore: ArrayList<String> = arrayListOf()) {
        this.type = type
        this.amount = amount
        this.displayName = displayName
        this.lore = lore
    }


    //===================<Lore>===================
    fun getLineInLore(line : Int): String {
        return this.lore[line]
    }

    fun addLore(lore : String) {
        this.lore.add(lore)
    }

    //===================<Enchantment>===================
    fun setEnchantment(enchantment : Enchantment, value : Int) {
        this.enchantment[enchantment] = value
    }

    fun removeEnchantment(enchantment : Enchantment) {
        this.enchantment.remove(enchantment)
    }

    //===================<ItemFlag>===================

    fun addItemFlag(itemFlag : ItemFlag) {
        this.itemFlag.add(itemFlag)
    }

    fun removeItemFlag(itemFlag : ItemFlag) {
        this.itemFlag.remove(itemFlag)
    }

    //===================<Attribute>===================
    fun addAttribute(attribute : Attribute, modifier : AttributeModifier) {
        this.attribute[attribute] = modifier
    }

    fun getAttributes(): HashMap<Attribute, AttributeModifier> {
        return this.attribute
    }

    fun removeAttribute(attribute : Attribute) {
        if(this.attribute.containsKey(attribute)) this.attribute.remove(attribute)
    }

    //===================<CanPlace>===================
    fun addCanPlace(canPlace : Material) {
        if(this.canPlace.contains(canPlace)) return
        this.canPlace.add(canPlace)
    }

    fun removeCanPlace(canPlace : Material) {
        if(!this.canPlace.contains(canPlace)) return
        this.canPlace.remove(canPlace)
    }

    //===================<CanDestroy>===================
    fun addCanDestroy(canDestroy : Material) {
        if(this.canDestroy.contains(canDestroy)) return
        this.canDestroy.add(canDestroy)
    }

    fun removeCanDestroy(canDestroy : Material) {
        if(!this.canDestroy.contains(canDestroy)) return
        this.canDestroy.remove(canDestroy)
    }



    //===================<Build>===================
    fun build(): ItemStack {
        val stack = ItemStack(type, amount)
        val meta = stack.itemMeta

        //DisplayName
        if (displayName != "") {
            meta.displayName(Component.text(displayName))
        }

        //Lore
        if (lore.isNotEmpty()) {
            meta.lore = lore
        }

        //Enchantment
        if(enchantment.isNotEmpty()) {
            for (e in enchantment) {
                meta.addEnchant(e.key, e.value, true)
            }
        }

        //CustomModelData
        if(customModelData != 0) {
            meta.setCustomModelData(customModelData)
        }

        //ItemFlag
        if(itemFlag.isNotEmpty()) {
            for(f in itemFlag) {
                meta.addItemFlags(f)
            }
        }

        //Unbreakable
        meta.isUnbreakable = unbreakable

        //Attribute
        if(attribute.isNotEmpty()) {
            for(a in attribute) {
                meta.addAttributeModifier(a.key, a.value)
            }
        }

        //CanPlace
        val place = mutableSetOf<Namespaced>()
        for (p in canPlace) {
            place.add(p.key)
        }
        meta.setPlaceableKeys(place)

        //CanDestroy
        val destroy = mutableSetOf<Namespaced>()
        for (d in canDestroy) {
            destroy.add(d.key)
        }
        meta.setDestroyableKeys(destroy)

        //Damage
        stack.durability = damage.toShort()


        stack.itemMeta = meta
        return stack
    }
}


fun ItemStack.asItemObject(): ItemObject {
    val lore = this.lore
    val nLore = arrayListOf<String>()
    if(lore != null) {
        for (l in lore) {
            nLore.add(l)
        }
    }

    //Type, Amount, DisplayName, Lore
    val itemObject = ItemObject(this.type, this.amount, this.itemMeta.displayName, nLore)

    //CustomModelData
    if(this.itemMeta.hasCustomModelData()) {
        itemObject.customModelData = this.itemMeta.customModelData
    }

    //Enchantments
    for(e in this.enchantments) {
        itemObject.setEnchantment(e.key, e.value)
    }

    //ItemFlag
    for(f in this.itemFlags) {
        itemObject.addItemFlag(f)
    }

    //CanPlace
    for(p in this.itemMeta.canPlaceOn) {
        itemObject.addCanPlace(p)
    }

    //CanDestroy
    for(d in this.itemMeta.canDestroy) {
        itemObject.addCanDestroy(d)
    }

    //Damage
    itemObject.damage = this.durability.toInt()

    return itemObject
}