package Charakters

import Fights.attackComputer
import characterComputer
import characterUser
import favoriteColorUser
import Fights.mainCharacterComputer
import Fights.mainCharacterUser
import Fights.randomAttackTeamUser
import reset
import selectionUserInt
import selectionUserString

open class CharacterWithGenjutsu : Character {

    var genjutsu: Int

    constructor(
        name: String,
        attack: MutableMap<String, Int>,
        ninjutsu: MutableMap<String, Int>,
        weapon: MutableMap<String, Int>,
        genjutsu: Int,
    ) : super(name, attack, ninjutsu, weapon) {

        this.genjutsu = genjutsu
    }

    // bei der Funktion wird dem Gegner der Wert des Schadens von dem Genjutsu von seinen Lebenspunkten abgezogen
    // und der Spieler verliert Chakra um den Wert der Attacke
    // hat er nicht genug Chakra, kann er diese Attacke nicht ausführen
    fun attackWithGenjutsu(enemy: Character) {


        if (chakra > genjutsu) {
            enemy.lifePoints -= this.genjutsu
            lostChakra(genjutsu)
            if (enemy == characterComputer) {
                selectionUserString = "Genjutsu"
            }
        } else {
            if (enemy == characterComputer) {
                println("\n\uD83D\uDE23 Du hast nicht genügend Chakra um ein Genjutsu auszuführen. Wähle erneut!")
                showSelection()
            } else {
                attackComputer()
            }

        }
    }

    // die Funktion aus Charakters.Character um die Möglichkeit ein Genjutsu anzuwenden erweitert
    override fun showSelection() {

        var counter = 0

        do {
            if (mainCharacterUser.name.isNotEmpty()) {
                if (ninjutsu.isEmpty()) {
                    println(
                        """
                    
            Womit möchtest du angreifen? $favoriteColorUser
            1 für Taijutsu
            2 für eine Waffe
            3 für Genjutsu 
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
                        characterUser.attackWithTaijutsu(selectionUserInt)
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
                        mainCharacterUser.attackWithWeapon(selectionUserInt)
                        counter = selectionUserInt

                    } else if (selectionUserInt == 3) {
                        attackWithGenjutsu(mainCharacterComputer)
                        counter = selectionUserInt

                    } else if (selectionUserInt == 4) {
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
            4 für Genjutsu 
            5 für Hilfe des Teams $reset
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
                        attackWithGenjutsu(mainCharacterComputer)
                        counter = selectionUserInt

                    } else if (selectionUserInt == 5) {
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
            2 für eine Waffe 
            3 für Genjutsu $reset
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
                    attackWithGenjutsu(characterComputer)
                    counter = selectionUserInt

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
            4 für Genjutsu $reset
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
                    attackWithGenjutsu(characterComputer)
                    counter = selectionUserInt

                } else {
                    println("\n❌ Du hast keine gültige Eingabe gemacht. Versuche es erneut!")
                    counter = 0
                }
            }
        } while (counter != selectionUserInt)
    }
}