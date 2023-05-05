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
    fun baumstamm(input: Any) {

        if (input == selectionUserInt) {
            if (this.baumstamm > 0) {
                baumstamm--
                println("Du bist ausgewichen!")
                selectionUserString = "Baumstamm"
            } else {
                println("\nDu hast Baumstamm bereits 5x angewendet und kannst es nicht mehr nutzen. Wähle erneut!")
                showSelection()
            }
        } else if (input == selectionComputer) {
            if (this.baumstamm > 0) {
                baumstamm--
                println("Der Computer ist ausgewichen!")
            } else {
                attackComputer()
            }
        }
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
            selectionUserInt = readln().toInt()

            if (selectionUserInt == 1) {
                println("\nDas hast du zur Auswahl:")
                var index = 1
                for (attack in attack.keys) {
                    println("$index für $attack")
                    index++
                }
                print("Triff deine Auswahl per Zahl: ")
                selectionUserInt = readln().toInt()
                attackNormal(selectionUserInt, characterComputer)
                counter = selectionUserInt
            } else if (selectionUserInt == 2) {
                println("\nDas hast du zur Auswahl:")
                var index = 1
                for (attack in ninjutsu.keys) {
                    println("$index für $attack")
                    index++
                }
                print("Triff deine Auswahl per Zahl: ")
                selectionUserInt = readln().toInt()
                attackWithNinjutsu(selectionUserInt, characterComputer)
                counter = selectionUserInt
            } else if (selectionUserInt == 3) {
                println("\nDas hast du zur Auswahl:")
                var index = 1
                for (attack in weapon.keys) {
                    println("$index für $attack")
                    index++
                }
                print("Triff deine Auswahl per Zahl: ")
                selectionUserInt = readln().toInt()
                attackWithWeapon(selectionUserInt, characterComputer)
                counter = selectionUserInt
            } else {
                println("Du hast keine gültige Eingabe gemacht. Versuche es erneut!")
                counter = 0
            }
        } while (counter != selectionUserInt)
    }

    // der Funktion werden 2 Parameter mitgegeben, die Eingabe des Spielers und den Gegner
    // je nach Auswahl wird dem Gegner der Schadenwert der jeweiligen Attacke von seinen Lebenspunkten abgezogen
    fun attackNormal(input: Any, enemy: Character) {

        when (input) {
            1 -> {
                enemy.lifePoints -= this.attack.values.elementAt(0)
                selectionUserString = this.attack.keys.elementAt(0)
            }

            2 -> {
                enemy.lifePoints -= this.attack.values.elementAt(1)
                selectionUserString = this.attack.keys.elementAt(1)
            }

            3 -> {
                enemy.lifePoints -= this.attack.values.elementAt(2)
                selectionUserString = this.attack.keys.elementAt(2)
            }

            4 -> {
                enemy.lifePoints -= this.attack.values.elementAt(3)
                selectionUserString = this.attack.keys.elementAt(3)
            }

            this.attack.keys.elementAt(0) -> enemy.lifePoints -= this.attack.values.elementAt(0)
            this.attack.keys.elementAt(1) -> enemy.lifePoints -= this.attack.values.elementAt(1)
            this.attack.keys.elementAt(2) -> enemy.lifePoints -= this.attack.values.elementAt(2)
            this.attack.keys.elementAt(3) -> enemy.lifePoints -= this.attack.values.elementAt(3)
        }
    }


    // der Funktion werden 2 Parameter mitgegeben, die Eingabe des Spielers und den Gegner
    // je nach Auswahl wird dem Gegner der Schadenwert der jeweiligen Attacke von seinen Lebenspunkten abgezogen
    // und der Spieler verliert Chakra bei dieser Art Attacke
    // hat er nicht genug Chakra, kann er diese Attacke nicht ausführen
    fun attackWithNinjutsu(input: Any, enemy: Character) {

        if (this.chakra >= 20) {
            when (input) {
                1 -> {
                    enemy.lifePoints -= this.ninjutsu.values.elementAt(0)
                    lostChakra(20)
                    selectionUserString = this.ninjutsu.keys.elementAt(0)
                }

                2 -> {
                    enemy.lifePoints -= this.ninjutsu.values.elementAt(1)
                    lostChakra(20)
                    selectionUserString = this.ninjutsu.keys.elementAt(1)
                }

                3 -> {
                    enemy.lifePoints -= this.ninjutsu.values.elementAt(2)
                    lostChakra(20)
                    selectionUserString = this.ninjutsu.keys.elementAt(2)
                }

                4 -> {
                    enemy.lifePoints -= this.ninjutsu.values.elementAt(3)
                    lostChakra(20)
                    selectionUserString = this.ninjutsu.keys.elementAt(3)
                }

                5 -> {
                    enemy.lifePoints -= this.ninjutsu.values.elementAt(4)
                    lostChakra(20)
                    selectionUserString = this.ninjutsu.keys.elementAt(4)
                }

                this.ninjutsu.keys.elementAt(0) -> {
                    enemy.lifePoints -= this.ninjutsu.values.elementAt(0)
                    lostChakra(20)
                }

                this.ninjutsu.keys.elementAt(1) -> {
                    enemy.lifePoints -= this.ninjutsu.values.elementAt(1)
                    lostChakra(20)
                }

                this.ninjutsu.keys.elementAt(2) -> {
                    enemy.lifePoints -= this.ninjutsu.values.elementAt(2)
                    lostChakra(20)
                }

                this.ninjutsu.keys.elementAt(3) -> {
                    enemy.lifePoints -= this.ninjutsu.values.elementAt(3)
                    lostChakra(20)
                }

                this.ninjutsu.keys.elementAt(4) -> {
                    enemy.lifePoints -= this.ninjutsu.values.elementAt(4)
                    lostChakra(20)
                }
            }
        } else {
            if (input == selectionUserInt) {
                println("\nDu hast nicht genügend Chakra um Ninjutsus anzuwenden. Wähle erneut!")
                showSelection()
            } else if (input == selectionComputer) {
                attackComputer()
            }
        }
    }

    // der Funktion werden 2 Parameter mitgegeben, die Eingabe des Spielers und den Gegner
    // je nach Auswahl wird dem Gegner der Schadenwert der jeweiligen Waffe von seinen Lebenspunkten abgezogen
    fun attackWithWeapon(input: Any, enemy: Character) {

        when (input) {
            1 -> {
                enemy.lifePoints -= this.weapon.values.elementAt(0)
                selectionUserString = this.weapon.keys.elementAt(0)
            }

            2 -> {
                enemy.lifePoints -= this.weapon.values.elementAt(1)
                selectionUserString = this.weapon.keys.elementAt(1)
            }

            3 -> {
                enemy.lifePoints -= this.weapon.values.elementAt(2)
                selectionUserString = this.weapon.keys.elementAt(2)
            }

            4 -> {
                enemy.lifePoints -= this.weapon.values.elementAt(3)
                selectionUserString = this.weapon.keys.elementAt(3)
            }

            this.weapon.keys.elementAt(0) -> enemy.lifePoints -= this.weapon.values.elementAt(0)
            this.weapon.keys.elementAt(1) -> enemy.lifePoints -= this.weapon.values.elementAt(1)
            this.weapon.keys.elementAt(2) -> enemy.lifePoints -= this.weapon.values.elementAt(2)
            this.weapon.keys.elementAt(3) -> enemy.lifePoints -= this.weapon.values.elementAt(3)
        }
    }
}
