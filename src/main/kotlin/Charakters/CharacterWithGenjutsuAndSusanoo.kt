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

class CharacterWithGenjutsuAndSusanoo : CharacterWithGenjutsu {

    val susanoo: Int

    constructor(
        name: String,
        attack: MutableMap<String, Int>,
        ninjutsu: MutableMap<String, Int>,
        weapon: MutableMap<String, Int>,
        genjutsu: Int, susanoo: Int,
    ) : super(name, attack, ninjutsu, weapon, genjutsu) {

        this.susanoo = susanoo
    }

    // Spieler greift mit Susanoo an und dem Gegner werden der entsprechende Wert von seinen lifePoints abgezogen
    fun attackWithSusanoo(enemy: Character) {

        if (chakra > susanoo) {
            if (selectionComputer != "Baumstamm") {
                enemy.lifePoints -= this.susanoo
            }
            lostChakra(susanoo)
            if (enemy == characterComputer) {
                selectionUserString = "Susanoo"
            }
        } else {
            if (enemy == characterComputer) {
                println("\n\uD83D\uDE23 Du hast nicht genügend Chakra um Susanoo zu erwecken. Wähle erneut!")
                showSelectionForSingle()
            } else {
                attackComputer()
            }

        }
    }

    // die Funktion aus Character um die Möglichkeit ein Genjutsu anzuwenden oder Susanoo zu erwecken erweitert
    override fun showSelectionForSingle() {

        var counter = false

        while (!counter) {
            try {
                if (mainCharacterUser.name.isNotEmpty()) {
                    if (ninjutsu.isEmpty()) {
                        println(
                            """
                    
            Womit möchtest du angreifen? $favoriteColorUser
            1 für Taijutsu
            2 für eine Waffe
            3 für Genjutsu 
            4 für Susanoo erwecken
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
                            characterUser.attackWithTaijutsu(selectionUserInt)
                            counter = true

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
                            counter = true

                        } else if (selectionUserInt == 3) {
                            attackWithGenjutsu(mainCharacterComputer)
                            counter = true

                        } else if (selectionUserInt == 4) {
                            attackWithSusanoo(mainCharacterComputer)
                            counter = true

                        } else if (selectionUserInt == 5) {
                            randomAttackTeamUser()
                        }
                    } else {
                        println(
                            """
                    
            Womit möchtest du angreifen? $favoriteColorUser
            1 für Taijutsu
            2 für ein Ninjutsu
            3 für eine Waffe
            4 für Genjutsu 
            5 für Susanoo erwecken
            6 für Hilfe des Teams $reset
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
                            counter = true

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
                            counter = true

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
                            counter = true

                        } else if (selectionUserInt == 4) {
                            attackWithGenjutsu(mainCharacterComputer)
                            counter = true

                        } else if (selectionUserInt == 5) {
                            attackWithSusanoo(mainCharacterComputer)

                        } else if (selectionUserInt == 6) {
                            randomAttackTeamUser()
                        }
                    }

                } else if (ninjutsu.isEmpty()) {
                    println(
                        """
                    
            Womit möchtest du angreifen? $favoriteColorUser
            1 für Taijutsu
            2 für eine Waffe 
            3 für Genjutsu 
            4 für Susanoo erwecken $reset
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
                        counter = true

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
                        counter = true

                    } else if (selectionUserInt == 3) {
                        attackWithGenjutsu(characterComputer)
                        counter = true

                    } else if (selectionUserInt == 4) {
                        attackWithSusanoo(characterComputer)
                        counter = true
                    }
                } else {
                    println(
                        """
                    
            Womit möchtest du angreifen? $favoriteColorUser
            1 für Taijutsu
            2 für ein Ninjutsu
            3 für eine Waffe 
            4 für Genjutsu 
            5 für Susanoo erwecken $reset
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
                        counter = true

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
                        counter = true

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
                        counter = true

                    } else if (selectionUserInt == 4) {
                        attackWithGenjutsu(characterComputer)
                        counter = true

                    } else if (selectionUserInt == 5) {
                        attackWithSusanoo(characterComputer)
                        counter = true
                    }
                }
            } catch (ex: Exception) {
                println("\n❌ Du hast keine gültige Eingabe gemacht. Versuche es erneut!")
            }
        }
    }
}