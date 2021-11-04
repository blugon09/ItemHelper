package io.github.blugon09.itemhelper

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.TextComponent
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextColor
import net.kyori.adventure.text.format.TextDecoration

class ColorText {
    val DARK_RED: NamedTextColor = NamedTextColor.DARK_RED
    val RED: NamedTextColor = NamedTextColor.RED
    val GOLD: NamedTextColor = NamedTextColor.GOLD
    val YELLOW: NamedTextColor = NamedTextColor.YELLOW
    val GREEN: NamedTextColor = NamedTextColor.GREEN
    val LIGHT_GREEN: NamedTextColor = NamedTextColor.DARK_GREEN
    val DARK_AQUA: NamedTextColor = NamedTextColor.DARK_AQUA
    val AQUA: NamedTextColor = NamedTextColor.AQUA
    val BLUE: NamedTextColor = NamedTextColor.BLUE
    val DARK_BLUE: NamedTextColor = NamedTextColor.DARK_BLUE
    val PINK: NamedTextColor = NamedTextColor.LIGHT_PURPLE
    val PURPLE: NamedTextColor = NamedTextColor.DARK_PURPLE
    val GRAY: NamedTextColor = NamedTextColor.GRAY
    val DARK_GRAY: NamedTextColor = NamedTextColor.DARK_GRAY
    val WHITE: NamedTextColor = NamedTextColor.WHITE
    val BLACK: NamedTextColor = NamedTextColor.BLACK
}


fun String.component(): TextComponent {
    return Component.text(this)
}

fun Component.text(): TextComponent {
    return this as TextComponent
}

fun TextComponent.bold(apply : Boolean): TextComponent {
    return this.decoration(TextDecoration.BOLD, apply)
}
fun TextComponent.italic(apply : Boolean): TextComponent {
    return this.decoration(TextDecoration.ITALIC, apply)
}
fun TextComponent.underlined(apply : Boolean): TextComponent {
    return this.decoration(TextDecoration.UNDERLINED, apply)
}
fun TextComponent.obfuscated(apply : Boolean): TextComponent {
    return this.decoration(TextDecoration.OBFUSCATED, apply)
}
fun TextComponent.strikethrough(apply : Boolean): TextComponent {
    return this.decoration(TextDecoration.STRIKETHROUGH, apply)
}

fun TextComponent.rgb(r: Int, g: Int, b: Int): TextComponent {
    return this.color(TextColor.color(TextColor.color(r, g, b)))
}