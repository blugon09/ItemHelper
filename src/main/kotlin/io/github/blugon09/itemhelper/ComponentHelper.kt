package io.github.blugon09.itemhelper

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.TextComponent
import net.kyori.adventure.text.format.TextColor
import net.kyori.adventure.text.format.TextDecoration


fun String.component(): TextComponent {
    return Component.text(this)
}

fun Component.asText(): TextComponent {
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