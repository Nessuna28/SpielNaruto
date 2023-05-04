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
    fun baumstamm() {

        baumstamm--
        println("Du bist ausgewichen!")
    }

    // der Spieler wird gefragt, womit er angreifen möchte
    // je nach seiner Antwort werden ihm die jeweiligen Attacken gezeigt und er kann dort auch wieder auswählen
    // je nach dem für was sich der Spieler entschieden hat, werden die jeweiligen Funktionen aufgerufen
    open fun showSelection() {

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
                println("\nDas hast du zur Auswahl:")
                var index = 1
                for (attack in attack.keys) {
                    println("$index für $attack")
                    index++
                }
                print("Triff deine Auswahl per Zahl: ")
                inputUserInt = readln().toInt()
                characterUser.attackNormal(inputUserInt, characterComputer)
                counter = inputUserInt
            } else if (inputUserInt == 2) {
                println("\nDas hast du zur Auswahl:")
                var index = 1
                for (attack in ninjutsu.keys) {
                    println("$index für $attack")
                    index++
                }
                print("Triff deine Auswahl per Zahl: ")
                inputUserInt = readln().toInt()
                characterUser.attackWithNinjutsu(inputUserInt, characterComputer)
                counter = inputUserInt
            } else if (inputUserInt == 3) {
                println("\nDas hast du zur Auswahl:")
                var index = 1
                for (attack in weapon.keys) {
                    println("$index für $attack")
                    index++
                }
                print("Triff deine Auswahl per Zahl: ")
                inputUserInt = readln().toInt()
                characterUser.attackWithWeapon(inputUserInt, characterComputer)
                counter = inputUserInt
            } else {
                println("Du hast keine gültige Eingabe gemacht. Versuche es erneut!")
                counter = 0
            }
        } while (counter != inputUserInt)
    }

    // der Funktion werden 2 Parameter mitgegeben, die Eingabe des Spielers und den Gegner
    // je nach Auswahl wird dem Gegner der Schadenwert der jeweiligen Attacke von seinen Lebenspunkten abgezogen
    fun attackNormal(input: Int, enemy: Character) {

        when (input) {
            0 -> enemy.lifePoints -= this.attack.values.elementAt(0)
            1 -> enemy.lifePoints -= this.attack.values.elementAt(1)
            2 -> enemy.lifePoints -= this.attack.values.elementAt(2)
            3 -> enemy.lifePoints -= this.attack.values.elementAt(3)
        }
    }

    // der Funktion werden 2 Parameter mitgegeben, die Eingabe des Spielers und den Gegner
    // je nach Auswahl wird dem Gegner der Schadenwert der jeweiligen Attacke von seinen Lebenspunkten abgezogen
    // und der Spieler verliert Chakra bei dieser Art Attacke
    // hat er nicht genug Chakra, kann er diese Attacke nicht ausführen
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

    // der Funktion werden 2 Parameter mitgegeben, die Eingabe des Spielers und den Gegner
    // je nach Auswahl wird dem Gegner der Schadenwert der jeweiligen Waffe von seinen Lebenspunkten abgezogen
    fun attackWithWeapon(input: Int, enemy: Character) {

        when (input) {
            0 -> enemy.lifePoints -= this.attack.values.elementAt(0)
            1 -> enemy.lifePoints -= this.attack.values.elementAt(1)
            2 -> enemy.lifePoints -= this.attack.values.elementAt(2)
            3 -> enemy.lifePoints -= this.attack.values.elementAt(3)

        }
    }
}
