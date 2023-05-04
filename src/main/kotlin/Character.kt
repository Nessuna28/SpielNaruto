open class Character(
    var name: String,
    var attack: MutableMap<String, Int>,
    var ninjutsu: MutableMap<String, Int>,
    var weapon: MutableMap<String, Int>,
) {

    var lifePoints = 500
    var chakra = 500
    var baumstamm = 5

    // Lebenspunkte werden um einen bestimmten Wert verringert
    fun lostLifePoints(value: Int) {

        lifePoints -= value
    }

    // Chakra wird um einen bestimmten Wert verringert
    fun lostChakra(value: Int) {

        chakra -= value
    }

    // einfaches Ausweichen
    // der Spieler hat nur 5 Mal die Möglichkeit Baumstamm einzusetzen, nach jedem Mal wird einmal abgezogen
    open fun baumstamm() {

        baumstamm--
        println("Du bist ausgewichen!")
    }

    fun showSelection() {

        var counter = 0

        do {
            println(
                """
            Womit möchtest du angreifen?
            1 für Taijutsu
            2 für ein Ninjutsu
            3 für eine Waffe
        """.trimIndent()
            )
            print("Gib die jeweilige Zahl ein: ")
            inputUserInt = readln().toInt()

            if (inputUserInt == 1) {
                for (attack in attack.keys) {
                    println("\n$attack")
                }
                counter = inputUserInt
            } else if (inputUserInt == 2) {
                for (attack in ninjutsu.keys) {
                    println("\n$attack")
                }
                counter = inputUserInt
            } else if (inputUserInt == 3) {
                for (attack in weapon.keys) {
                    println("\n$attack")
                }
                counter = inputUserInt
            } else {
                println("Du hast keine gültige Eingabe gemacht. Versuche es erneut!")
                counter = 0
            }
        } while (counter != inputUserInt)
    }

    fun attackNormal(input: Int, enemy: Character) {

        when (input) {
            0 -> enemy.lifePoints -= this.attack.values.elementAt(0)
            1 -> enemy.lifePoints -= this.attack.values.elementAt(1)
            2 -> enemy.lifePoints -= this.attack.values.elementAt(2)
            3 -> enemy.lifePoints -= this.attack.values.elementAt(3)
        }
    }

    fun attackWithNinjutsu(input: Int, enemy: Character) {

        if (this.chakra >= 20) {
            when (input) {
                0 -> {
                    enemy.lifePoints -= this.attack.values.elementAt(0)
                    lostChakra(20)
                }

                1 -> {
                    enemy.lifePoints -= this.attack.values.elementAt(1)
                    lostChakra(20)
                }

                2 -> {
                    enemy.lifePoints -= this.attack.values.elementAt(2)
                    lostChakra(20)
                }

                3 -> {
                    enemy.lifePoints -= this.attack.values.elementAt(3)
                    lostChakra(20)
                }

                4 -> {
                    enemy.lifePoints -= this.attack.values.elementAt(4)
                    lostChakra(20)
                }
            }
        } else {
            println("\nDu hast nicht genügend Chakra um Ninjutsus anzuwenden. Wähle erneut!")
        }
    }

    fun attackWithWeapon(input: Int, enemy: Character) {

        when (input) {
            0 -> enemy.lifePoints -= this.attack.values.elementAt(0)
            1 -> enemy.lifePoints -= this.attack.values.elementAt(1)
            2 -> enemy.lifePoints -= this.attack.values.elementAt(2)
            3 -> enemy.lifePoints -= this.attack.values.elementAt(3)

        }
    }
}
