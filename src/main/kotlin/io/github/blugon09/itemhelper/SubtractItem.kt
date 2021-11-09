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
    var total = 0

    for(i in 0 until this.size) {
        if(this.getItem(i) != null) {
            val subtractitem = this.getItem(i)!!
            if(subtractitem.type == subtractItemType) {
                if(amount <= subtractitem.amount) {
                    this.getItem(i)!!.amount = this.getItem(i)!!.amount-amount
                    return true
                } else {
                    total += amount
                    if(amount <= total) {
                        for(i2 in 0 until this.size) {
                            if(this.getItem(i) == null) continue
                            val subtract = this.getItem(i)!!

                            if(subtract.type != subtractItemType) continue
                            for(i3 in 0..subtract.amount) {
                                if(amount < total) {
                                    total--
                                    subtract.amount -= 1
                                } else {
                                    return true
                                }
                            }
                        }
                    } else {
                        return true
                    }
                }
            }
        }
    }
    return false
}


fun Inventory.isExistType(exist : Material) : Boolean {
    for(i in 0 until this.size) {
        if(this.getItem(i) == null) continue
        val subtractitem = this.getItem(i)!!

        if (subtractitem.type != exist) continue
        return true
    }
    return false
}


fun Inventory.isExistType(exist : Material, amount : Int) : Boolean {
    var total = 0

    for(i in 0 until this.size) {
        if(this.getItem(i) == null) continue
        val subtractitem = this.getItem(i)!!

        if(subtractitem.type != exist) continue
        if(amount <= subtractitem.amount) {
            this.getItem(i)!!.amount = this.getItem(i)!!.amount-amount
            return true
        } else {
            total += amount
            if(amount <= total) {
                for(i2 in 0 until this.size) {
                    if(this.getItem(i) == null) continue
                    val subtract = this.getItem(i)!!
                    if(subtract.type != exist) continue

                    for(i3 in 0..subtract.amount) {
                        if(amount < total) {
                            total--
                        } else {
                            return true
                        }
                    }
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