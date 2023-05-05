open class Character(
    var name: String,
    var attack: MutableMap<String, Int>,
    var ninjutsu: MutableMap<String, Int>,
    var weapon: MutableMap<String, Int>,
) {

    var lifePointStart = 500
    var lifePoints = 500
    var chakra = 500
    var baumstamm = 5

    // der Funktion werden 2 Parameter mitgegeben, die Eingabe des Users oder die Auswahl des Computers und der Gegner
    // Lebenspunkte des Gegners werden um den Wert der Attacke verringert
    fun lostLifePoints(input: String, enemy: Character) {

        if (input != "Baumstamm") {
            when (input) {
                this.attack.keys.elementAt(0) -> enemy.lifePoints -= this.attack.values.elementAt(0)
                this.attack.keys.elementAt(1) -> enemy.lifePoints -= this.attack.values.elementAt(1)
                this.attack.keys.elementAt(2) -> enemy.lifePoints -= this.attack.values.elementAt(2)
                this.attack.keys.elementAt(3) -> enemy.lifePoints -= this.attack.values.elementAt(3)
                this.ninjutsu.keys.elementAt(0) -> enemy.lifePoints -= this.ninjutsu.values.elementAt(0)
                this.ninjutsu.keys.elementAt(1) -> enemy.lifePoints -= this.ninjutsu.values.elementAt(1)
                this.ninjutsu.keys.elementAt(2) -> enemy.lifePoints -= this.ninjutsu.values.elementAt(2)
                this.ninjutsu.keys.elementAt(3) -> enemy.lifePoints -= this.ninjutsu.values.elementAt(3)
                this.ninjutsu.keys.elementAt(4) -> enemy.lifePoints -= this.ninjutsu.values.elementAt(4)
                this.weapon.keys.elementAt(0) -> enemy.lifePoints -= this.weapon.values.elementAt(0)
                this.weapon.keys.elementAt(1) -> enemy.lifePoints -= this.weapon.values.elementAt(1)
                this.weapon.keys.elementAt(2) -> enemy.lifePoints -= this.weapon.values.elementAt(2)
                this.weapon.keys.elementAt(3) -> enemy.lifePoints -= this.weapon.values.elementAt(3)
            }
        } else {
            if (enemy == characterUser) {
                println("\nDer Computer ist ausgewichen.")
            } else if (enemy == characterComputer) {
                println("\nDu bist ausgewichen.")
            }
        }
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
                selectionUserString = "Baumstamm"
            } else {
                println("\nDu hast Baumstamm bereits 5x angewendet und kannst es nicht mehr nutzen. Wähle erneut!")
                showSelection()
            }
        } else if (input == selectionComputer) {
            if (this.baumstamm > 0) {
                baumstamm--
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
                attackNormal(selectionUserInt)
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
                attackWithNinjutsu(selectionUserString, selectionUserInt)
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
                attackWithWeapon(selectionUserInt)
                counter = selectionUserInt
            } else {
                println("Du hast keine gültige Eingabe gemacht. Versuche es erneut!")
                counter = 0
            }
        } while (counter != selectionUserInt)
    }

    // der Funktion wird ein Parameter mitgegeben, die Eingabe des Spielers
    // je nach Auswahl der Zahl wird die dementsprechende Attacke in der Variablen selectionUser gespeichert zum Weiterbearbeiten
    fun attackNormal(input: Int) {

        when (input) {
            1 -> selectionUserString = this.attack.keys.elementAt(0)
            2 -> selectionUserString = this.attack.keys.elementAt(1)
            3 -> selectionUserString = this.attack.keys.elementAt(2)
            4 -> selectionUserString = this.attack.keys.elementAt(3)
        }
    }


    // der Funktion wird ein Parameter mitgegeben, die Eingabe des Spielers
    // je nach Auswahl wird dem Spieler Chakra abgezogen um den Wert der Attacke und die dementsprechende Attacke in der Variablen selectionUser gespeichert zum Weiterbearbeiten
    // hat er nicht genug Chakra, kann er diese Attacke nicht ausführen
    fun attackWithNinjutsu(attack: String, input: Int) {

            if (input == selectionUserInt) {
                when (input) {
                    1 -> {
                        selectionUserString = this.ninjutsu.keys.elementAt(0)
                        notEnoughChakra("user")
                    }

                    2 -> {
                        selectionUserString = this.ninjutsu.keys.elementAt(1)
                        notEnoughChakra("user")
                    }

                    3 -> {
                        selectionUserString = this.ninjutsu.keys.elementAt(2)
                        notEnoughChakra("user")
                    }

                    4 -> {
                        selectionUserString = this.ninjutsu.keys.elementAt(3)
                        notEnoughChakra("user")
                    }

                    5 -> {
                        selectionUserString = this.ninjutsu.keys.elementAt(4)
                        notEnoughChakra("user")
                    }
                }
            } else if (attack == selectionComputer) {
                when (attack) {
                    this.ninjutsu.keys.elementAt(0) -> {

                        lostChakra(valueOfAttack)
                    }
                    this.ninjutsu.keys.elementAt(1) -> lostChakra(valueOfAttack)
                    this.ninjutsu.keys.elementAt(2) -> lostChakra(valueOfAttack)
                    this.ninjutsu.keys.elementAt(3) -> lostChakra(valueOfAttack)
                    this.ninjutsu.keys.elementAt(4) -> lostChakra(valueOfAttack)
                }
                attackComputer()
            }
    }

    // der Funktion wird ein Parameter mitgegeben, die Eingabe des Spielers
    // je nach Auswahl wird die dementsprechende Attacke in der Variablen selectionUser gespeichert zum Weiterbearbeiten
    fun attackWithWeapon(input: Int) {

        when (input) {
            1 -> selectionUserString = this.weapon.keys.elementAt(0)
            2 -> selectionUserString = this.weapon.keys.elementAt(1)
            3 -> selectionUserString = this.weapon.keys.elementAt(2)
            4 -> selectionUserString = this.weapon.keys.elementAt(3)
        }
    }

    fun notEnoughChakra(input: String){

        var valueOfAttack = 0

        if (input == "user") {
            valueOfAttack = ninjutsu[selectionUserString]!!
        } else {
            valueOfAttack = ninjutsu[selectionComputer]!!
        }
        if (this.chakra >= valueOfAttack) {
            lostChakra(valueOfAttack)
        } else {
            if (input == "user") {
                println("\nDu hast nicht genügend Chakra um Ninjutsus anzuwenden. Wähle erneut!")
                showSelection()
            }
        }
    }


}
