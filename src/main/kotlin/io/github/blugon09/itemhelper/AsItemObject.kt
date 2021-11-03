package io.github.blugon09.itemhelper

import org.bukkit.inventory.ItemStack

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
    itemObject.customModelData = this.itemMeta.customModelData

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