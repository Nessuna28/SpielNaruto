package Fights

import Charakters.*
import blue
import characterComputer
import characterUser
import counterRounds
import counterWins
import favoriteColorUser
import lifePointsComputer
import lifePointsUser
import nameUser
import reset
import selectionComputer
import selectionUserInt
import selectionUserString

fun fight() {}

// diese Funktion fragt den Spieler, ob er angreifen oder ausweichen möchte und ruft je nach Antwort die dazugehörigen Funktionen auf
fun selectionAttackUser() {

    var counter = false

    while (!counter) {
    try {
        if (characterUser.name.isNotEmpty()) {
            print("\nMöchtest du $favoriteColorUser(1) angreifen$reset oder $favoriteColorUser(2) ausweichen$reset? Gib die jeweilige Zahl ein: ")
            selectionUserInt = readln().toInt()
            if (selectionUserInt == 1) {
                characterUser.showSelectionForSingle()
                counter = true
            } else if (selectionUserInt == 2) {
                characterUser.baumstamm("user")
                counter = true
            } else {
                println("\n❌ Du hast keine gültige Eingabe gemacht. Versuche es erneut!")
            }
        }
    } catch (ex: Exception) {
        println("\n❌ Du hast keine gültige Eingabe gemacht. Versuche es erneut!")
    }
}

    characterUser.loadChakra(selectionUserString)
}

// diese Funktion wählt ein Angriff oder Verteidigung per Zufall aus je nachdem welchen Charakter der Computer hat
// und speichert die Auswahl dann in der Variablen selectionComputer, in der Main
fun attackComputer() {

    val attackList = mutableListOf<String>()

    if (characterComputer is CharacterWithGenjutsu) {
        if (characterComputer is CharacterWithGenjutsuAndSusanoo) {
            for (attack in characterComputer.taijutsu)
                attackList.add(attack.key)

            for (attack in characterComputer.ninjutsu)
                attackList.add(attack.key)

            for (attack in characterComputer.weapon)
                attackList.add(attack.key)

            attackList.add("Genjutsu")
            attackList.add("Susanoo")
            attackList.add("Baumstamm")
    } else {
        for (attack in characterComputer.taijutsu)
            attackList.add(attack.key)

        for (attack in characterComputer.ninjutsu)
            attackList.add(attack.key)

        for (attack in characterComputer.weapon)
            attackList.add(attack.key)

        attackList.add("Baumstamm")
        attackList.add("Genjutsu")
    }
    } else if (characterComputer is CharacterWithMedicalSkills) {
        for (attack in characterComputer.taijutsu)
            attackList.add(attack.key)

        for (attack in characterComputer.ninjutsu)
            attackList.add(attack.key)

        for (attack in characterComputer.weapon)
            attackList.add(attack.key)

        attackList.add("Baumstamm")
        attackList.add("Heilung")

    } else if (characterComputer is CharacterWithBijuu) {
        for (attack in characterComputer.taijutsu)
            attackList.add(attack.key)

        for (attack in characterComputer.ninjutsu)
            attackList.add(attack.key)

        for (attack in characterComputer.weapon)
            attackList.add(attack.key)

        attackList.add((characterComputer as CharacterWithBijuu).bijuu.first)
        attackList.add("Baumstamm")
        attackList.add("Bijuu")

    } else {
        for (attack in characterComputer.taijutsu)
            attackList.add(attack.key)

        for (attack in characterComputer.ninjutsu)
            attackList.add(attack.key)

        for (attack in characterComputer.weapon)
            attackList.add(attack.key)

        attackList.add("Baumstamm")
    }

    selectionComputer = attackList.random()

    if (selectionComputer == "Genjutsu")
        (characterComputer as CharacterWithGenjutsu).attackWithGenjutsu(characterUser)

    if (selectionComputer == "Heilung")
        (characterComputer as CharacterWithMedicalSkills).heal(selectionComputer)

    if (selectionComputer == "Baumstamm")
        characterComputer.baumstamm("com")

    if (selectionComputer in characterComputer.ninjutsu)
        characterComputer.attackWithNinjutsu(selectionComputer, 0)

    if (selectionComputer == "Susanoo")
        (characterComputer as CharacterWithGenjutsuAndSusanoo).attackWithSusanoo(characterUser)

    if (characterComputer is CharacterWithBijuu) {
        if (selectionComputer == (characterComputer as CharacterWithBijuu).bijuu.first)
            (characterComputer as CharacterWithBijuu).attackWithBijuu(characterUser)
    }

    characterComputer.loadChakra(selectionComputer)
}

// der Spieler wird gefragt, womit er angreifen möchte
// je nach seiner Antwort werden ihm die jeweiligen Attacken gezeigt und er kann dort auch wieder auswählen
// je nach dem für was sich der Spieler entschieden hat, werden die jeweiligen Funktionen aufgerufen
/*fun showSelectionForCharacter() {

    var counter = false

    while (!counter) {
        try {
            if (characterUser.name.isNotEmpty()) {
                if (characterUser.ninjutsu.isEmpty()) {
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
                        for (attack in characterUser.taijutsu.keys) {
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
                        for (attack in characterUser.weapon.keys) {
                            println("$favoriteColorUser$index für $attack $reset")
                            index++
                        }
                        print("Triff deine Auswahl per Zahl: ")
                        selectionUserInt = readln().toInt()
                        characterUser.attackWithWeapon(selectionUserInt)
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
            3 für eine Waffe $reset
        """.trimIndent()
                    )

                    print("Gib die jeweilige Zahl ein: ")
                    selectionUserInt = readln().toInt()

                    if (selectionUserInt == 1) {
                        println("\nDas hast du zur Auswahl:")
                        var index = 1
                        for (attack in characterUser.taijutsu.keys) {
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
                        for (attack in characterUser.ninjutsu.keys) {
                            println("$favoriteColorUser$index für $attack $reset")
                            index++
                        }
                        print("Triff deine Auswahl per Zahl: ")
                        selectionUserInt = readln().toInt()
                        characterUser.attackWithNinjutsu(selectionUserString, selectionUserInt)
                        counter = true

                    } else if (selectionUserInt == 3) {
                        println("\nDas hast du zur Auswahl:")
                        var index = 1
                        for (attack in characterUser.weapon.keys) {
                            println("$favoriteColorUser$index für $attack $reset")
                            index++
                        }
                        print("Triff deine Auswahl per Zahl: ")
                        selectionUserInt = readln().toInt()
                        characterUser.attackWithWeapon(selectionUserInt)
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

fun showSelectionForCharacterWithBijuu() {

    val bijuuName = characterUser.bijuu.first
    var counter = false

    while (!counter) {
        try {
            if (characterUser.name.isNotEmpty()) {
                if (characterUser.ninjutsu.isEmpty()) {
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
                        for (attack in characterUser.taijutsu.keys) {
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
                        for (attack in characterUser.weapon.keys) {
                            println("$favoriteColorUser$index für $attack $reset")
                            index++
                        }
                        print("Triff deine Auswahl per Zahl: ")
                        selectionUserInt = readln().toInt()
                        characterUser.attackWithWeapon(selectionUserInt)
                        counter = true

                    } else if (selectionUserInt == 3) {
                        characterUser.attackWithBijuu(characterComputer)
                        counter = true
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
                        for (attack in characterUser.taijutsu.keys) {
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
                        for (attack in characterComputer.ninjutsu.keys) {
                            println("$favoriteColorUser$index für $attack $reset")
                            index++
                        }
                        print("Triff deine Auswahl per Zahl: ")
                        selectionUserInt = readln().toInt()
                        characterUser.attackWithNinjutsu(selectionUserString, selectionUserInt)
                        counter = true

                    } else if (selectionUserInt == 3) {
                        println("\nDas hast du zur Auswahl:")
                        var index = 1
                        for (attack in characterUser.weapon.keys) {
                            println("$favoriteColorUser$index für $attack $reset")
                            index++
                        }
                        print("Triff deine Auswahl per Zahl: ")
                        selectionUserInt = readln().toInt()
                        characterUser.attackWithWeapon(selectionUserInt)
                        counter = true

                    } else if (selectionUserInt == 4) {
                        characterUser.attackWithBijuu(characterComputer)
                        counter = true
                    }
                }
            }
        } catch (ex: Exception) {
            println("\n❌ Du hast keine gültige Eingabe gemacht. Versuche es erneut!")
        }
    }
}


 */
// der Funktion werden 4 Parameter mitgegeben, die Attacken der Spieler und die jeweiligen Charaktere,
// wenn die Spieler sich nicht für das Ausweichen entscheiden, werden die Lebenspunkte des Gegners um den Wert der Attacke verringert
// zum Schluss werden die Lebenspunkte der jeweiligen Spieler in einer Variablen außerhalb der Main gespeichert
fun lostLifePointsSinglePlay(attackUser: String, attackComputer: String, enemyUser: Character, enemyComputer: Character) {

    if (characterUser.name.isNotEmpty()) {
        if (attackComputer != "Baumstamm") {

            var index = 0

            for (attack in characterUser.taijutsu.keys) {
                if (attackUser == attack) {
                    enemyComputer.lifePoints -= characterUser.taijutsu.values.elementAt(index)
                    break
                }
                index++
            }

            index = 0
            for (attack in characterUser.ninjutsu.keys) {
                if (attackUser == attack) {
                    enemyComputer.lifePoints -= characterUser.ninjutsu.values.elementAt(index)
                    break
                }
                index++
            }

            index = 0
            for (attack in characterUser.weapon.keys) {
                if (attackUser == attack) {
                    enemyComputer.lifePoints -= characterUser.weapon.values.elementAt(index)
                    break
                }
                index++
            }
        }

        if (attackUser != "Baumstamm") {

            var index = 0

            for (attack in characterComputer.taijutsu.keys) {
                if (attackComputer == attack) {
                    enemyUser.lifePoints -= characterComputer.taijutsu.values.elementAt(index)
                    break
                }
                index++
            }

            index = 0
            for (attack in characterComputer.ninjutsu.keys) {
                if (attackComputer == attack) {
                    enemyUser.lifePoints -= characterComputer.ninjutsu.values.elementAt(index)
                    break
                }
                index++
            }

            index = 0
            for (attack in characterComputer.weapon.keys) {
                if (attackComputer == attack) {
                    enemyUser.lifePoints -= characterComputer.weapon.values.elementAt(index)
                    break
                }
                index++
            }
        }

        if (lifePointsUser < 0)
            lifePointsUser = 0

        if (lifePointsComputer < 0)
            lifePointsComputer = 0

        lifePointsUser = characterUser.lifePoints
        lifePointsComputer = characterComputer.lifePoints
    }
}

fun lostLifePointsTeamPlay(attackUser: String, attackComputer: String, mainEnemyUser: Character, mainEnemyComputer: Character) {

    if (mainCharacterUser.name.isNotEmpty()) {
        if (attackComputer != "Baumstamm") {

            var index = 0

            for (attack in mainCharacterUser.taijutsu.keys) {
                if (attackUser == attack) {
                    mainEnemyComputer.lifePoints -= mainCharacterUser.taijutsu.values.elementAt(index)
                    break
                }
                index++
            }

            index = 0
            for (attack in mainCharacterUser.ninjutsu.keys) {
                if (attackUser == attack) {
                    mainEnemyComputer.lifePoints -= mainCharacterUser.ninjutsu.values.elementAt(index)
                    break
                }
                index++
            }

            index = 0
            for (attack in mainCharacterUser.weapon.keys) {
                if (attackUser == attack) {
                    mainEnemyComputer.lifePoints -= mainCharacterUser.weapon.values.elementAt(index)
                    break
                }
                index++
            }
        }

        if (attackUser != "Baumstamm") {

            var index = 0

            for (attack in mainCharacterComputer.taijutsu.keys) {
                if (attackComputer == attack) {
                    mainEnemyUser.lifePoints -= mainEnemyUser.taijutsu.values.elementAt(index)
                    break
                }
                index++
            }

            index = 0
            for (attack in mainCharacterComputer.ninjutsu.keys) {
                if (attackComputer == attack) {
                    mainEnemyUser.lifePoints -= mainCharacterComputer.ninjutsu.values.elementAt(index)
                    break
                }
                index++
            }

            index = 0
            for (attack in mainCharacterComputer.weapon.keys) {
                if (attackComputer == attack) {
                    mainEnemyUser.lifePoints -= mainCharacterComputer.weapon.values.elementAt(index)
                    break
                }
                index++
            }
        }

        lifePointsUser = mainCharacterUser.lifePoints
        lifePointsComputer = mainCharacterComputer.lifePoints
    }
}

// diese Funktion zeigt die Spielerdaten in einer Println an
fun valueOfCharacterPrint() {

    if (characterUser.name.isNotEmpty()) {
        println(
            """
        
    -------------------------------------------------------------------------------------------------------------------------------------------------------    
    Spieler 1:     $favoriteColorUser$nameUser ${reset}          |          Spieler 2:     ${blue}Computer $reset
                                  |
    Charakter:     $favoriteColorUser${characterUser.name} ${reset}        |          Charakter:     $blue${characterComputer.name} $reset
    Lebenspunkte:  $favoriteColorUser${characterUser.lifePoints} $reset/ ${characterUser.lifePointStart}      |          Lebenspunkte:  $blue${characterComputer.lifePoints} $reset/ ${characterComputer.lifePointStart}
    Chakra:        $favoriteColorUser${characterUser.chakra} $reset/ ${characterUser.chakraStart}      |          Chakra:        $blue${characterComputer.chakra} $reset/ ${characterComputer.chakraStart}
    Baumstamm:     $favoriteColorUser${characterUser.baumstamm} $reset/ 5          |          Baumstamm:     $blue${characterComputer.baumstamm} $reset/ 5
    -------------------------------------------------------------------------------------------------------------------------------------------------------
    """.trimIndent()
        )

    } else if (mainCharacterUser.name.isNotEmpty()) {
        println(
            """
        
    -------------------------------------------------------------------------------------------------------------------------------------------------------    
    Spieler 1:     $favoriteColorUser$nameUser ${reset}          |          Spieler 2:     ${blue}Computer $reset
                                  |
    Charakter:     $favoriteColorUser${mainCharacterUser.name} ${reset}        |          Charakter:     $blue${mainCharacterComputer.name} $reset
    Lebenspunkte:  $favoriteColorUser${mainCharacterUser.lifePoints} $reset/ ${mainCharacterUser.lifePointStart}      |          Lebenspunkte:  $blue${mainCharacterComputer.lifePoints} $reset/ ${mainCharacterComputer.lifePointStart}
    Chakra:        $favoriteColorUser${mainCharacterUser.chakra} $reset/ ${mainCharacterUser.chakraStart}      |          Chakra:        $blue${mainCharacterComputer.chakra} $reset/ ${mainCharacterComputer.chakraStart}
    Baumstamm:     $favoriteColorUser${mainCharacterUser.baumstamm} $reset/ 5          |          Baumstamm:     $blue${mainCharacterComputer.baumstamm} $reset/ 5
    -------------------------------------------------------------------------------------------------------------------------------------------------------
    """.trimIndent()
        )
    }
}

// diese Funktion gibt einen entsprechenden Text aus bei Nutzung der Abwehr
fun defensePrint() {

    if (selectionComputer == "Baumstamm") {
        if (selectionUserString == "Baumstamm") {
            println("\nDer Computer ist ebenfalls ausgewichen")
        } else {
            if (selectionUserString != "Heilung") {
                println("\nDu wolltest mit ${favoriteColorUser}${selectionUserString}${reset} angreifen aber der Computer ist ausgewichen.")
            } else {
                println("\nDer Computer ist ausgewichen.")
            }
        }
    } else {
        if (selectionUserString == "Baumstamm") {
            println("\nDer Computer wollte mit ${blue}${selectionComputer}${reset} angreifen aber du bist ausgewichen. \uD83D\uDE1D")
        }
    }
}

// diese Funktion gibt die Attacke des Spielers in einer Println aus, wenn der Gegner nicht ausgewichen ist oder der Spieler Heilung benutzt hat
fun wichAttackUserPrint() {

        if (selectionUserString != "Baumstamm" && selectionUserString != "Heilung" && selectionComputer != "Baumstamm" && selectionComputer != "Heilung") {
            println("\nDu hast mit $favoriteColorUser$selectionUserString$reset angegriffen und deinen Gegner getroffen.")
            if (selectionComputer == "Heilung") {
                println("\nDu hast mit $favoriteColorUser$selectionUserString$reset angegriffen aber der Gegner hat sich geheilt.")
            }
    }
}

// diese Funktion gibt die Attacke des Computers in einer Println aus, wenn der Spieler nicht ausgewichen ist oder der Computer Heilung benutzt hat
fun whichAttackComputerPrint() {

    if (selectionComputer != "Baumstamm" && selectionComputer != "Heilung" && selectionUserString != "Baumstamm") {
        println("Dein Gegner hat mit $blue$selectionComputer$reset angegriffen und dich getroffen.")
    }
}

// diese Funktion gibt kleine Bildchen aus für die jeweiligen Attacken des Spielers
fun grafikForAttack() {

    if (selectionUserString.lowercase().contains("feuer"))
        println("\n        ☄\uFE0F \n")

    if (selectionUserString.lowercase().contains("schlafbombe"))
        println("\n        \uD83D\uDCA3 \n")

    if (selectionUserString.lowercase().contains("baum"))
        println("\n        \uD83E\uDEB5 \n")

    if (selectionUserString.lowercase().contains("blüte"))
        println("\n         \uD83C\uDF38 \n")

    if (selectionUserString.lowercase().contains("wind"))
        println("\n         \uD83C\uDF2A\uFE0F \n")

    if (selectionUserString.lowercase().contains("schwert"))
        println("\n         \uD83D\uDDE1\uFE0F \n")

    if (selectionUserString.lowercase().contains("wasser"))
        println("\n         \uD83C\uDF0A \n")

    if (selectionUserString.lowercase().contains("punch"))
        println("\n         \uD83D\uDC4A\uD83C\uDFFC \n")

    if (selectionUserString.lowercase().contains("kick"))
        println("\n         \uD83E\uDDB6\uD83C\uDFFC \n")

    if (selectionUserString.lowercase().contains("genjutsu"))
         println("\n        \uD83C\uDF00 \n       \uD83D\uDE35\u200D\uD83D\uDCAB \n")

    if (selectionUserString.lowercase().contains("strategie"))
        println("\n         \uD83E\uDDD0 \n")

    if (selectionUserString.lowercase().contains("fleisch"))
        println("\n         \uD83C\uDF56 \n")

    if (selectionUserString.lowercase().contains("insekt"))
        println("\n         \uD83D\uDC1C \uD83E\uDD9F \uD83D\uDC1D \n")

    if (selectionUserString.lowercase().contains("erd"))
        println("\n         \uD83E\uDEA8 \n")

    if (selectionUserString.lowercase().contains("schlange"))
        println("\n         \uD83D\uDC0D \n")

    if (selectionUserString.lowercase().contains("feuerball"))
        println("\n         ☄\uFE0F \n")

    if (selectionUserString.lowercase().contains("vertrauten")) {
        if (characterUser == kakashi)
            println("\n         \uD83D\uDC15 \n")
        if (characterUser == naruto)
            println("\n         \uD83D\uDC38 \n")
        if (characterUser == tsunade)
            println("\n         \uD83D\uDC0C \n")
        if (characterUser == orochimaru)
            println("\n         \uD83D\uDC0D \n")
        if (characterUser == jiraiya)
            println("\n         \uD83D\uDC38 \n")
    }


    if (selectionUserString.lowercase().contains("susanoo"))
    println("\n             \uD83D\uDC80 \n")

    if (selectionUserString.lowercase().contains("kurama"))
        println("\n          \uD83E\uDD8A \n")

    if (selectionUserString.lowercase().contains("shukaka"))
        println("\n          ฅ՞•ﻌ•՞ฅ \n")
}

// diese Funktion sagt dem Spieler, ob er gewonnen oder verloren hat und zählt die Runden und die Siege
fun winOrLosePrint() {

    var winsRoundOrRounds = ""
    var roundOrRounds = ""




    if (characterComputer.lifePoints <= 0) {
        counterWins++
        counterRounds++
        if (counterWins == 1 )
            winsRoundOrRounds = "Runde"
        else
            winsRoundOrRounds = "Runden"

        if (counterRounds == 1)
            roundOrRounds = "Runde"
        else
            roundOrRounds = "Runden"
        println("\nDer Computer ist gefallen und steht nicht mehr auf. \n")
        Thread.sleep(2000)
        println("""
            
                         🏆 

        🎇 $favoriteColorUser Super! Du hast gewonnen. $reset 🎇
         Du hast ${favoriteColorUser}$counterWins $winsRoundOrRounds ${reset}von $counterRounds $roundOrRounds gewonnen. 👏   
          
        """.trimIndent()
        )

    } else if (characterUser.lifePoints <= 0) {
        counterRounds++
        if (counterWins == 1 )
            winsRoundOrRounds = "Runde"
        else
            winsRoundOrRounds = "Runden"

        if (counterRounds == 1)
            roundOrRounds = "Runde"
        else
            roundOrRounds = "Runden"
        println("\n Du bist gefallen und stehst nicht mehr auf. \n")
        Thread.sleep(2000)
        println("\n")
        println("""
            
        😔 $favoriteColorUser Schade! Du hast leider verloren. $reset 😔
        Du hast ${favoriteColorUser}$counterWins $winsRoundOrRounds ${reset}von $counterRounds $roundOrRounds gewonnen. 👏
            
        """.trimIndent()
        )
    }
}


