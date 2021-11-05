package io.github.blugon09.itemhelper

import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.Inventory

fun Inventory.subtractItemByType(subtractItemType : Material) : Boolean {
    for(i in 0 until this.size) {
        if(this.getItem(i) != null) {
            val subtractitem = this.getItem(i)!!
            if(subtractitem.type == subtractItemType) {
                if(1 <= subtractitem.amount) {
                    this.getItem(i)!!.amount = this.getItem(i)!!.amount-1
                    return true
                }
            }
        }
    }
    return false
}


fun Inventory.subtractItemByType(subtractItemType : Material, amount : Int) : Boolean {
    for(i in 0 until this.size) {
        if(this.getItem(i) != null) {
            val subtractitem = this.getItem(i)!!
            if(subtractitem.type == subtractItemType) {
                if(amount <= subtractitem.amount) {
                    this.getItem(i)!!.amount = this.getItem(i)!!.amount-amount
                    return true
                }
            }
        }
    }
    return false
}




fun Player.subtractItemByType(subtractItemType : Material, amount : Int) : Boolean {
    return this.inventory.subtractItemByType(subtractItemType, amount)
}


fun Player.subtractItemByType(subtractItemType : Material) : Boolean {
    return this.inventory.subtractItemByType(subtractItemType)
}