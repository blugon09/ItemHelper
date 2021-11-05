package io.github.blugon09.itemhelper

import com.destroystokyo.paper.Namespaced
import com.google.common.collect.Multimap
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.TextComponent
import org.bukkit.Material
import org.bukkit.attribute.Attribute
import org.bukkit.attribute.AttributeModifier
import org.bukkit.enchantments.Enchantment
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack

class ItemObject {
    var type = Material.AIR
    var amount = 1
    var displayName : TextComponent? = Component.text("")
    var lore = arrayListOf<TextComponent>()
    val enchantment = HashMap<Enchantment, Int>()
    var customModelData = 0
    val itemFlag = arrayListOf<ItemFlag>()
    var unbreakable = false
    var attribute = HashMap<Attribute, ArrayList<AttributeModifier>>()
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
    constructor(type: Material = Material.AIR, amount: Int = 1, displayName: TextComponent? = null) {
        this.type = type
        this.amount = amount
        this.displayName = displayName
    }
    constructor(type: Material = Material.AIR, amount: Int = 1, displayName: TextComponent? = null, lore: ArrayList<TextComponent> = arrayListOf()) {
        this.type = type
        this.amount = amount
        this.displayName = displayName
        this.lore = lore
    }



    //===================<Lore>===================
    fun getLineInLore(line : Int): TextComponent {
        return this.lore[line]
    }

    fun addLore(lore : TextComponent) {
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
        if(this.attribute[attribute] != null) {
            this.attribute[attribute]!!.add(modifier)
        } else {
            this.attribute[attribute] = arrayListOf(modifier)
        }
    }

    fun getAttributes(): HashMap<Attribute, ArrayList<AttributeModifier>> {
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
        if (displayName != null) {
            meta.displayName(displayName)
        }

        //Lore
        if (lore.isNotEmpty()) {
            meta.lore(lore as List<Component>)
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
                for(m in a.value) {
                    meta.addAttributeModifier(a.key, m)
                }
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
    val itemObject = ItemObject(this.type, this.amount)

    //DisplayName
    if(this.itemMeta.hasDisplayName()) {
        itemObject.displayName = this.itemMeta.displayName() as TextComponent
    }

    //Lore
    if(this.itemMeta.lore() != null) {
        if(this.itemMeta.lore()!!.isNotEmpty()) {
            for(l in this.itemMeta.lore()!!) {
                itemObject.addLore(l as TextComponent)
            }
        }
    }

    //Unbreakable
    itemObject.unbreakable = this.itemMeta.isUnbreakable

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

    //Attribute
    if(this.itemMeta.attributeModifiers != null) {
        if(!this.itemMeta.attributeModifiers!!.isEmpty) {
            val mulimap = this.itemMeta.attributeModifiers!!
            val attributes = this.itemMeta.attributeModifiers!!.asMap()!!

            for(a in attributes) {
                for(m in mulimap[a.key]) {
                    itemObject.addAttribute(a.key, m)
                }
            }
        }
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