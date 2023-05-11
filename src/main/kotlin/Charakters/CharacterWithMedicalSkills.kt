package Charakters

import Fights.attackComputer
import blue
import blueBackground
import characterUser
import favoriteColorUser
import greenBackground
import greyBackground
import Fights.mainCharacterUser
import Fights.randomAttackTeamUser
import reset
import selectionComputer
import selectionUserInt
import selectionUserString

class CharacterWithMedicalSkills : Character {

    var medicalSkills: Int

    constructor(
        name: String,
        attack: MutableMap<String, Int>,
        ninjutsu: MutableMap<String, Int>,
        weapon: MutableMap<String, Int>,
        medicalSkills: Int,
    ) :
            super(name, attack, ninjutsu, weapon) {

        this.medicalSkills = medicalSkills
    }

    // diese Funktion ermöglicht es dem Spieler sich zu heilen in dem seinen lifePoints den Wert des Skills dazu gerechnet werden
    // aber mehr als 500 (Anfangswert) gehen nicht
    // diese Fähigkeit verbraucht Chakra um den Wert des Skills
    fun heal(input: String) {

        if (input == selectionUserString) {
            if (this.chakra >= medicalSkills) {
                if (lifePoints < lifePointStart) {
                    this.lifePoints += medicalSkills
                    lostChakra(medicalSkills)
                    if (lifePoints > lifePointStart) {
                        lifePoints = lifePointStart
                    }
                    coloredBar()
                    println("\n\uD83D\uDCAA\uD83C\uDFFC ${favoriteColorUser} Du wurdest geheilt! $reset")
                } else if (this.lifePoints == lifePointStart) {
                    println("\n\uD83E\uDD14 Deine Lebenspunkte sind voll. Diese Auswahl war unnötig.")
                }
                selectionUserString = "Heilung"
            } else {
                println("\n\uD83D\uDE23 Du hast nicht genügend Chakra um dich zu heilen. Wähle erneut!")
                showSelectionForSingle()
            }
        } else if (input == selectionComputer) {
            if (this.chakra >= medicalSkills) {
                if (lifePoints < lifePointStart) {
                    this.lifePoints += medicalSkills
                    lostChakra(medicalSkills)
                    if (lifePoints > lifePointStart) {
                        lifePoints = lifePointStart
                    }
                    println("\n${blue}Der Computer wurde geheilt! $reset")
                }
            } else {
                attackComputer()
            }
        }
    }

    // in der Funktion wird ein farbiger Balken generiert für die Funktion heal
    fun coloredBar() {

        val life = lifePoints / 50
        val to = life + (medicalSkills / 50)
        val end = lifePointStart / 50

        var coloredBar = StringBuilder()
        coloredBar.append("$greyBackground| | | | | | | | | | ")

        print(coloredBar)

        for (point in 0..life) {
            if (point < end) {
                coloredBar.append("$blueBackground | |$reset")
                print("\r$coloredBar")
                Thread.sleep(800)
            }

            if (point > to) {
                coloredBar.append("$greenBackground | |$reset")
                print("\r$coloredBar")
                Thread.sleep(800)

            }
        }
    }

    // die Funktion aus Character um die Möglichkeit der Heilung erweitert
    override fun showSelectionForSingle() {

        var counter = false

        while (!counter) {
            try {
                if (characterUser.name.isNotEmpty()) {
                    if (ninjutsu.isEmpty()) {
                        println(
                            """
                    
            Womit möchtest du angreifen? $favoriteColorUser
            1 für Taijutsu
            2 für eine Waffe 
            3 für Heilung $reset
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
                            heal(selectionUserString)
                            counter = true
                        } else {
                            println("\n❌ Du hast keine gültige Eingabe gemacht. Versuche es erneut!")
                        }

                    } else {
                        println(
                            """
                    
            Womit möchtest du angreifen? $favoriteColorUser
            1 für Taijutsu
            2 für ein Ninjutsu
            3 für eine Waffe 
            4 für Heilung $reset
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
                            heal(selectionUserString)
                            counter = true
                        } else {
                            println("\n❌ Du hast keine gültige Eingabe gemacht. Versuche es erneut!")
                        }
                    }
                }
            } catch (ex: Exception) {
                println("\n❌ Du hast keine gültige Eingabe gemacht. Versuche es erneut!")
            }
        }
    }

    override fun showSelectionForTeam() {

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
            3 für Heilung 
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
                            heal(selectionUserString)
                            counter = true

                        } else if (selectionUserInt == 4) {
                            randomAttackTeamUser()
                        } else {
                            println("\n❌ Du hast keine gültige Eingabe gemacht. Versuche es erneut!")
                        }

                    } else {
                        println(
                            """
                    
            Womit möchtest du angreifen? $favoriteColorUser
            1 für Taijutsu
            2 für ein Ninjutsu
            3 für eine Waffe
            4 für Heilung 
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
                            heal(selectionUserString)
                            counter = true

                        } else if (selectionUserInt == 5) {
                            randomAttackTeamUser()
                        } else {
                            println("\n❌ Du hast keine gültige Eingabe gemacht. Versuche es erneut!")
                        }
                    }
                }
            } catch (ex: Exception) {
                println("\n❌ Du hast keine gültige Eingabe gemacht. Versuche es erneut!")
            }
        }
    }
}