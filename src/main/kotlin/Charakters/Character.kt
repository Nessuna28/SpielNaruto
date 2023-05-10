package Charakters

import Fights.attackComputer
import characterComputer
import characterUser
import favoriteColorUser
import Fights.mainCharacterUser
import Fights.randomAttackTeamUser
import reset
import selectionComputer
import selectionUserInt
import selectionUserString

open class Character(
    var name: String,
    var taijutsu: MutableMap<String, Int>,
    var ninjutsu: MutableMap<String, Int>,
    var weapon: MutableMap<String, Int>,
) {

    val lifePointStart = 500
    val chakraStart = 500
    var lifePoints = 500
    var chakra = 500
    var baumstamm = 5

   /* fun lostLifePointsSinglePlay(attackPlayer: String, attackEnemy: String, enemy: Charakters.Character) {

        if (characterUser.name.isNotEmpty()) {
            if (attackPlayer != "Baumstamm") {

                var index = 0

                for (attack in enemy.taijutsu.keys) {
                    if (attackEnemy == attack) {
                        lifePoints -= enemy.taijutsu.values.elementAt(index)
                        break
                    }
                    index++
                }

                index = 0
                for (attack in enemy.ninjutsu.keys) {
                    if (attackEnemy == attack) {
                        lifePoints -= enemy.ninjutsu.values.elementAt(index)
                        break
                    }
                    index++
                }

                index = 0
                for (attack in enemy.weapon.keys) {
                    if (attackEnemy == attack) {
                        lifePoints -= enemy.weapon.values.elementAt(index)
                        break
                    }
                    index++
                }
            }

            lifePointsUser = characterUser.lifePoints
            lifePointsComputer = characterComputer.lifePoints
        }
    }

    */

    // Chakra wird um einen bestimmten Wert verringert
    fun lostChakra(value: Int) {

        chakra -= value
    }

    // Chakra läd sich auf, wenn der Angriff keine Attacke ist die Chakra verbraucht
    fun loadChakra(selectAttack: String) {

        for (attack in this.taijutsu.keys) {
            if (attack == selectAttack) {
                if (chakra < chakraStart) {
                    chakra += 10
                    if (chakra > chakraStart) {
                        chakra = chakraStart
                    }
                }
                break
            }
        }

        for (attack in weapon.keys) {
            if (attack == selectAttack) {
                if (chakra < chakraStart) {
                    chakra += 10
                    if (chakra > chakraStart) {
                        chakra = chakraStart
                    }
                }
                break
            }
        }
    }

    // einfaches Ausweichen
    // der Spieler hat nur 5 Mal die Möglichkeit Baumstamm einzusetzen, nach jedem Mal wird einmal abgezogen
    fun baumstamm(input: String) {

        if (this.baumstamm > 0) {
            baumstamm--
            if (input == "user") {
                selectionUserString = "Baumstamm"
            }
        } else {
            if (input == "user") {
                println("\nDu hast Baumstamm bereits 5x angewendet und kannst es nicht mehr nutzen. Wähle erneut!")
                showSelection()
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
            if (mainCharacterUser.name.isNotEmpty()) {
                if (ninjutsu.isEmpty()) {
                    println(
                        """
                    
            Womit möchtest du angreifen? $favoriteColorUser
            1 für Taijutsu
            2 für eine Waffe 
            3 für Hilfe des Teams $reset
        """.trimIndent()
                    )

                    print("Gib die jeweilige Zahl ein: ")
                    selectionUserInt = readln().toInt()

                    if (selectionUserInt == 1) {
                        println("\nDas hast du zur Auswahl:")
                        var index = 1
                        for (attack in taijutsu.keys) {
                            println("$favoriteColorUser$index für $attack $reset")
                            index++
                        }
                        print("Triff deine Auswahl per Zahl: ")
                        selectionUserInt = readln().toInt()
                        attackWithTaijutsu(selectionUserInt)
                        counter = selectionUserInt

                    } else if (selectionUserInt == 2) {
                        println("\nDas hast du zur Auswahl:")
                        var index = 1
                        for (attack in weapon.keys) {
                            println("$favoriteColorUser$index für $attack $reset")
                            index++
                        }
                        print("Triff deine Auswahl per Zahl: ")
                        selectionUserInt = readln().toInt()
                        attackWithWeapon(selectionUserInt)
                        counter = selectionUserInt

                    } else if (selectionUserInt == 3) {
                        randomAttackTeamUser()


                    } else {
                        println("\n❌ Du hast keine gültige Eingabe gemacht. Versuche es erneut!")
                        counter = 0
                    }
                } else {
                    println(
                        """
                    
            Womit möchtest du angreifen? $favoriteColorUser
            1 für Taijutsu
            2 für ein Ninjutsu
            3 für eine Waffe
            4 für Hilfe des Teams $reset
        """.trimIndent()
                    )

                    print("Gib die jeweilige Zahl ein: ")
                    selectionUserInt = readln().toInt()

                    if (selectionUserInt == 1) {
                        println("\nDas hast du zur Auswahl:")
                        var index = 1
                        for (attack in taijutsu.keys) {
                            println("$favoriteColorUser$index für $attack $reset")
                            index++
                        }
                        print("Triff deine Auswahl per Zahl: ")
                        selectionUserInt = readln().toInt()
                        attackWithTaijutsu(selectionUserInt)
                        counter = selectionUserInt

                    } else if (selectionUserInt == 2) {
                        println("\nDas hast du zur Auswahl:")
                        var index = 1
                        for (attack in ninjutsu.keys) {
                            println("$favoriteColorUser$index für $attack $reset")
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
                            println("$favoriteColorUser$index für $attack $reset")
                            index++
                        }
                        print("Triff deine Auswahl per Zahl: ")
                        selectionUserInt = readln().toInt()
                        attackWithWeapon(selectionUserInt)
                        counter = selectionUserInt

                    } else if (selectionUserInt == 4) {
                        randomAttackTeamUser()

                    } else {
                        println("\n❌ Du hast keine gültige Eingabe gemacht. Versuche es erneut!")
                        counter = 0
                    }
                }
            } else if (ninjutsu.isEmpty()) {
                println(
                    """
                    
            Womit möchtest du angreifen? $favoriteColorUser
            1 für Taijutsu
            2 für eine Waffe $reset
        """.trimIndent()
                )

                print("Gib die jeweilige Zahl ein: ")
                selectionUserInt = readln().toInt()

                if (selectionUserInt == 1) {
                    println("\nDas hast du zur Auswahl:")
                    var index = 1
                    for (attack in taijutsu.keys) {
                        println("$favoriteColorUser$index für $attack $reset")
                        index++
                    }
                    print("Triff deine Auswahl per Zahl: ")
                    selectionUserInt = readln().toInt()
                    attackWithTaijutsu(selectionUserInt)
                    counter = selectionUserInt

                } else if (selectionUserInt == 2) {
                    println("\nDas hast du zur Auswahl:")
                    var index = 1
                    for (attack in weapon.keys) {
                        println("$favoriteColorUser$index für $attack $reset")
                        index++
                    }
                    print("Triff deine Auswahl per Zahl: ")
                    selectionUserInt = readln().toInt()
                    attackWithWeapon(selectionUserInt)
                    counter = selectionUserInt

                } else {
                    println("\n❌ Du hast keine gültige Eingabe gemacht. Versuche es erneut!")
                    counter = 0
                }
            } else if (characterUser.name.isNotEmpty()){
                println(
                    """
                    
            Womit möchtest du angreifen? $favoriteColorUser
            1 für Taijutsu
            2 für ein Ninjutsu
            3 für eine Waffe $reset
        """.trimIndent()
                )

                print("Gib die jeweilige Zahl ein: ")
                selectionUserInt = readln().toInt()

                if (selectionUserInt == 1) {
                    println("\nDas hast du zur Auswahl:")
                    var index = 1
                    for (attack in taijutsu.keys) {
                        println("$favoriteColorUser$index für $attack $reset")
                        index++
                    }
                    print("Triff deine Auswahl per Zahl: ")
                    selectionUserInt = readln().toInt()
                    attackWithTaijutsu(selectionUserInt)
                    counter = selectionUserInt

                } else if (selectionUserInt == 2) {
                    println("\nDas hast du zur Auswahl:")
                    var index = 1
                    for (attack in ninjutsu.keys) {
                        println("$favoriteColorUser$index für $attack $reset")
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
                        println("$favoriteColorUser$index für $attack $reset")
                        index++
                    }
                    print("Triff deine Auswahl per Zahl: ")
                    selectionUserInt = readln().toInt()
                    attackWithWeapon(selectionUserInt)
                    counter = selectionUserInt

                } else {
                    println("\n❌ Du hast keine gültige Eingabe gemacht. Versuche es erneut!")
                    counter = 0
                }
            }
        } while (counter != selectionUserInt)
    }

    // der Funktion wird ein Parameter mitgegeben, die Eingabe des Spielers
    // je nach Auswahl der Zahl wird die dementsprechende Attacke in der Variablen selectionUser gespeichert zum Weiterbearbeiten
    fun attackWithTaijutsu(input: Int) {

        if (input > taijutsu.size) {
            println("\n❌ Du hast keine gültige Eingabe gemacht. Versuche es erneut!")
            showSelection()
        } else {
            when (input) {
                1 -> selectionUserString = this.taijutsu.keys.elementAt(0)
                2 -> selectionUserString = this.taijutsu.keys.elementAt(1)
                3 -> selectionUserString = this.taijutsu.keys.elementAt(2)
                4 -> selectionUserString = this.taijutsu.keys.elementAt(3)
                5 -> selectionUserString = this.taijutsu.keys.elementAt(4)
            }
        }
    }

    // der Funktion wird ein Parameter mitgegeben, die Eingabe des Spielers
    // je nach Auswahl wird dem Spieler Chakra abgezogen um den Wert der Attacke und die dementsprechende Attacke in der Variablen selectionUser gespeichert zum Weiterbearbeiten
    // hat er nicht genug Chakra, kann er diese Attacke nicht ausführen
    fun attackWithNinjutsu(attack: String, input: Int) {

        if (input > ninjutsu.size) {
            println("\n❌ Du hast keine gültige Eingabe gemacht. Versuche es erneut!")
            showSelection()
        } else {
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
            }
        }

        if (attack == selectionComputer) {
            when (attack) {
                this.ninjutsu.keys.elementAt(0) -> notEnoughChakra("com")
                this.ninjutsu.keys.elementAt(1) -> notEnoughChakra("com")
                this.ninjutsu.keys.elementAt(2) -> notEnoughChakra("com")
                this.ninjutsu.keys.elementAt(3) -> notEnoughChakra("com")
                this.ninjutsu.keys.elementAt(4) -> notEnoughChakra("com")
            }
        }
    }

    // der Funktion wird ein Parameter mitgegeben, die Eingabe des Spielers
    // je nach Auswahl wird die dementsprechende Attacke in der Variablen selectionUser gespeichert zum Weiterbearbeiten
    fun attackWithWeapon(input: Int) {

        if (input > weapon.size) {
            println("\n❌ Du hast keine gültige Eingabe gemacht. Versuche es erneut!")
            showSelection()
        } else {
            when (input) {
                1 -> selectionUserString = this.weapon.keys.elementAt(0)
                2 -> selectionUserString = this.weapon.keys.elementAt(1)
                3 -> selectionUserString = this.weapon.keys.elementAt(2)
                4 -> selectionUserString = this.weapon.keys.elementAt(3)
            }
        }
    }

    // diese Funktion guckt ob der Spieler genügend Chakra für den Angriff hat,
    // wenn, ja wird ihm Chakra um den Wert der Attacke abgezogen, wenn nicht, dann bekommt er gesagt, dass er nicht genügend Chakra hat
    fun notEnoughChakra(input: String) {

        var valueOfAttack = 0

        if (input == "user") {
            if (selectionUserString in ninjutsu) {
                valueOfAttack = ninjutsu[selectionUserString]!!
                if (this.chakra >= valueOfAttack) {
                    lostChakra(valueOfAttack)
                } else {
                    println("\n\uD83D\uDE2B Du hast nicht genügend Chakra um Ninjutsus anzuwenden. Wähle erneut!")
                    showSelection()
                }
            } else if (selectionUserString == "susanoo") {
                valueOfAttack = (characterUser as CharacterWithGenjutsuAndSusanoo).susanoo
                if (this.chakra >= valueOfAttack) {
                    lostChakra(valueOfAttack)
                } else {
                    println("\n\uD83D\uDE2B Du hast nicht genügend Chakra um Ninjutsus anzuwenden. Wähle erneut!")
                    showSelection()
                }
            } else if (selectionUserString == (characterUser as CharacterWithBijuu).bijuu.first) {
                valueOfAttack = (characterUser as CharacterWithBijuu).bijuu.second
                if (this.chakra >= valueOfAttack) {
                    lostChakra(valueOfAttack)
                }
            }
        } else {
            if (selectionComputer in ninjutsu) {
                valueOfAttack = ninjutsu[selectionComputer]!!
                if (this.chakra >= valueOfAttack)
                    lostChakra(valueOfAttack)
            } else if (selectionComputer == "susanoo") {
                valueOfAttack = (characterComputer as CharacterWithGenjutsuAndSusanoo).susanoo
                if (this.chakra >= valueOfAttack)
                    lostChakra(valueOfAttack)
            } else if (selectionComputer == (characterComputer as CharacterWithBijuu).bijuu.first) {
                valueOfAttack = (characterComputer as CharacterWithBijuu).bijuu.second
                if (this.chakra >= valueOfAttack)
                    lostChakra(valueOfAttack)
            } else {
                attackComputer()
            }
        }
    }
}
