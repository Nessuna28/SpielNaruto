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
            enemy.lifePoints -= this.susanoo
            lostChakra(susanoo)
            if (enemy == characterComputer) {
                selectionUserString = "Susanoo"
            }
        } else {
            if (enemy == characterComputer) {
                println("\n\uD83D\uDE23 Du hast nicht genügend Chakra um Susanoo zu erwecken. Wähle erneut!")
                showSelection()
            } else {
                attackComputer()
            }

        }
    }

    // die Funktion aus Character um die Möglichkeit ein Genjutsu anzuwenden oder Susanoo zu erwecken erweitert
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
                            println("$index für $attack")
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
                            println("$index für $attack")
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
                        attackWithSusanoo(mainCharacterComputer)
                        counter = selectionUserInt

                    } else if (selectionUserInt == 5) {
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
                            println("$index für $attack")
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

                    } else if (selectionUserInt == 4) {
                        attackWithGenjutsu(mainCharacterComputer)
                        counter = selectionUserInt

                    } else if (selectionUserInt == 5) {
                        attackWithSusanoo(mainCharacterComputer)

                    } else if (selectionUserInt == 6) {
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
                        println("$index für $attack")
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
                        println("$index für $attack")
                        index++
                    }
                    print("Triff deine Auswahl per Zahl: ")
                    selectionUserInt = readln().toInt()
                    attackWithWeapon(selectionUserInt)
                    counter = selectionUserInt

                } else if (selectionUserInt == 3) {
                    attackWithGenjutsu(characterComputer)
                    counter = selectionUserInt

                } else if (selectionUserInt == 4) {
                    attackWithSusanoo(characterComputer)
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
                        println("$index für $attack")
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

                } else if (selectionUserInt == 4) {
                    attackWithGenjutsu(characterComputer)
                    counter = selectionUserInt

                } else if (selectionUserInt == 5) {
                    attackWithSusanoo(characterComputer)
                    counter = selectionUserInt

                } else {
                    println("\n❌ Du hast keine gültige Eingabe gemacht. Versuche es erneut!")
                    counter = 0
                }
            }
        } while (counter != selectionUserInt)
    }
}