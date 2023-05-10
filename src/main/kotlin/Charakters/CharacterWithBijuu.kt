package Charakters

import Fights.attackComputer
import characterComputer
import characterUser
import favoriteColorUser
import Fights.mainCharacterComputer
import Fights.mainCharacterUser
import Fights.randomAttackTeamUser
import reset
import selectionComputer
import selectionUserInt
import selectionUserString

class CharacterWithBijuu: Character {

    var bijuu: Pair<String, Int>


    constructor(name: String, attack: MutableMap<String, Int>, ninjutsu: MutableMap<String, Int>, weapon: MutableMap<String, Int>, bijuu: Pair<String, Int>):
            super(name, attack, ninjutsu, weapon) {

                this.bijuu = bijuu
            }

    fun attackWithBijuu(enemy: Character) {

        val bijuuName = this.bijuu.first
        val bijuuValue = this.bijuu.second


        if (chakra > bijuuValue) {
            if (selectionComputer != "Baumstamm") {
                enemy.lifePoints -= bijuuValue
            }
            lostChakra(bijuuValue)
            if (enemy == characterComputer) {
                selectionUserString = bijuuName
            }
        } else {
            if (enemy == characterComputer) {
                println("\n\uD83D\uDE23 Du hast nicht genügend Chakra um dein Bijuu zu erwecken. Wähle erneut!")
                showSelection()
            } else {
                attackComputer()
            }

        }
    }

    // die Funktion aus Character um die Möglichkeit ein Bijuu oder Kami zu erwecken erweitert
    override fun showSelection() {

        val bijuuName = this.bijuu.first
        var counter = 0

        do {
            if (mainCharacterUser.name.isNotEmpty()) {
                if (ninjutsu.isEmpty()) {
                    println(
                        """
                    
            Womit möchtest du angreifen? $favoriteColorUser
            1 für Taijutsu
            2 für eine Waffe
            3 für $bijuuName erwecken 
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
                        attackWithBijuu(mainCharacterComputer)
                        counter = selectionUserInt

                    } else if (selectionUserInt == 4) {
                        randomAttackTeamUser()

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
            3 für eine Waffe
            4 für $bijuuName erwecken 
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
                        attackWithBijuu(mainCharacterComputer)
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
            3 für $bijuuName erwecken $reset
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
                    attackWithBijuu(characterComputer)
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
            4 für $bijuuName erwecken $reset
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
                    attackWithBijuu(characterComputer)
                    counter = selectionUserInt

                } else {
                    println("\n❌ Du hast keine gültige Eingabe gemacht. Versuche es erneut!")
                    counter = 0
                }
            }
        } while (counter != selectionUserInt)
    }
}