package Fights

import Charakters.*
import characterComputer
import favoriteColorUser
import grafik
import lifePointsComputer
import lifePointsUser
import reset
import selectionComputer
import selectionUserInt
import selectionUserString
import teamComputer
import teamUser

fun teamFight() {}

var mainCharacterUser = Character("", mutableMapOf(), mutableMapOf(), mutableMapOf())
var teamMember1User = Character("", mutableMapOf(), mutableMapOf(), mutableMapOf())
var attackTeamMember1User = ""
var teamMember2User = Character("", mutableMapOf(), mutableMapOf(), mutableMapOf())
var attackTeamMember2User = ""

var mainCharacterComputer = Character("", mutableMapOf(), mutableMapOf(), mutableMapOf())
var teamMember1Computer = Character("", mutableMapOf(), mutableMapOf(), mutableMapOf())
var attackTeamMember1Computer = ""
var teamMember2Computer = Character("", mutableMapOf(), mutableMapOf(), mutableMapOf())
var attackTeamMember2Computer = ""


// diese Funktion fragt den Spieler, ob er angreifen oder ausweichen möchte und ruft je nach Antwort die dazugehörigen Funktionen auf
// am Ende wird die Funktion aufgerufen um das Chakra wieder aufzuladen
fun selectionAttackTeamUser() {

    if (mainCharacterUser.name.isNotEmpty()) {
        var counter = 0

        do {
            print("\nMöchtest du $favoriteColorUser(1) angreifen$reset oder $favoriteColorUser(2) ausweichen$reset? Gib die jeweilige Zahl ein: ")
            selectionUserInt = readln().toInt()
            if (selectionUserInt == 1) {
                mainCharacterUser.showSelectionForTeam()
                counter = selectionUserInt
            } else if (selectionUserInt == 2) {
                mainCharacterUser.baumstamm("user")
                counter = selectionUserInt
            } else {
                println("\n❌ Du hast keine gültige Eingabe gemacht. Versuche es erneut!")
                counter = 0
            }

    } while (counter != selectionUserInt)

        mainCharacterUser.loadChakra(selectionUserString)
}
}

// Spieler soll sich aus dem Team einen Hauptcharakter aussuchen, der wird danach aus der Teamliste gelöscht und in der Variablen mainCharacterUser gespeichert
fun selectionMainCharacter() {

    println("\nWelcher soll dein Hauptcharakter sein?")
    print("Gib den Namen ein! : ")
    val selectionUser = readln().lowercase()
    setCharacterForTeam(selectionUser)
    teamUser.remove(mainCharacterUser)
    selectionUserString = mainCharacterUser.name


    teamMember1User = teamUser.random()
    teamUser.remove(teamMember1User)

    teamMember2User = teamUser.random()

    grafik(mainCharacterUser.name)
}

// diese Funktion bestimmt einen Hauptcharakter für den Computer, der wird danach aus der Teamliste gelöscht und in der Variablen mainCharacterComputer gespeichert
fun selectionMainCharacterComputer() {

    do {
        mainCharacterComputer = teamComputer.random()
        teamComputer.remove(mainCharacterComputer)
    } while (mainCharacterComputer == mainCharacterUser)

    characterComputer = mainCharacterComputer

}

// diese Funktion sucht für die beiden Charaktere aus dem Team zufällige Attacken aus
// und zieht danach die jeweiligen Werte von den Lebenspunkten ab
fun randomAttackTeamUser() {

    val attackListTeamMember1 = mutableListOf<String>()
    val attackListTeamMember2 = mutableListOf<String>()


    if (teamMember1User is CharacterWithGenjutsu) {
        if (teamMember1User is CharacterWithGenjutsuAndSusanoo) {
            for (attack in teamMember1User.taijutsu)
                attackListTeamMember1.add(attack.key)

            for (attack in teamMember1User.ninjutsu)
                attackListTeamMember1.add(attack.key)

            for (attack in teamMember1User.weapon)
                attackListTeamMember1.add(attack.key)

        } else {
            for (attack in teamMember1User.taijutsu)
                attackListTeamMember1.add(attack.key)

            for (attack in teamMember1User.ninjutsu)
                attackListTeamMember1.add(attack.key)

            for (attack in teamMember1User.weapon)
                attackListTeamMember1.add(attack.key)
        }
    } else if (teamMember1User is CharacterWithMedicalSkills) {
        for (attack in teamMember1User.taijutsu)
            attackListTeamMember1.add(attack.key)

        for (attack in teamMember1User.ninjutsu)
            attackListTeamMember1.add(attack.key)

        for (attack in teamMember1User.weapon)
            attackListTeamMember1.add(attack.key)

        attackListTeamMember1.add("Heilung")

    } else if (teamMember1User is CharacterWithBijuu) {
        for (attack in teamMember1User.taijutsu)
            attackListTeamMember1.add(attack.key)

        for (attack in teamMember1User.ninjutsu)
            attackListTeamMember1.add(attack.key)

        for (attack in teamMember1User.weapon)
            attackListTeamMember1.add(attack.key)

    } else {
        for (attack in teamMember1User.taijutsu)
            attackListTeamMember1.add(attack.key)

        for (attack in teamMember1User.ninjutsu)
            attackListTeamMember1.add(attack.key)

        for (attack in teamMember1User.weapon)
            attackListTeamMember1.add(attack.key)
    }


    if (teamMember2User is CharacterWithGenjutsu) {
        if (teamMember2User is CharacterWithGenjutsuAndSusanoo) {
            for (attack in teamMember2User.taijutsu)
                attackListTeamMember2.add(attack.key)

            for (attack in teamMember2User.ninjutsu)
                attackListTeamMember2.add(attack.key)

            for (attack in teamMember2User.weapon)
                attackListTeamMember2.add(attack.key)

        } else {
            for (attack in teamMember2User.taijutsu)
                attackListTeamMember2.add(attack.key)

            for (attack in teamMember2User.ninjutsu)
                attackListTeamMember2.add(attack.key)

            for (attack in teamMember2User.weapon)
                attackListTeamMember2.add(attack.key)
        }
    } else if (teamMember2User is CharacterWithMedicalSkills) {
        for (attack in teamMember2User.taijutsu)
            attackListTeamMember2.add(attack.key)

        for (attack in teamMember2User.ninjutsu)
            attackListTeamMember2.add(attack.key)

        for (attack in teamMember2User.weapon)
            attackListTeamMember2.add(attack.key)

        attackListTeamMember2.add("Heilung")

    } else if (teamMember2User is CharacterWithBijuu) {
        for (attack in teamMember2User.taijutsu)
            attackListTeamMember2.add(attack.key)

        for (attack in teamMember2User.ninjutsu)
            attackListTeamMember2.add(attack.key)

        for (attack in teamMember2User.weapon)
            attackListTeamMember2.add(attack.key)

    } else {
        for (attack in teamMember2User.taijutsu)
            attackListTeamMember2.add(attack.key)

        for (attack in teamMember2User.ninjutsu)
            attackListTeamMember2.add(attack.key)

        for (attack in teamMember2User.weapon)
            attackListTeamMember2.add(attack.key)
    }

    attackTeamMember1User = attackListTeamMember1.random()
    attackTeamMember2User = attackListTeamMember2.random()


    if (attackTeamMember1User == "Heilung")
        mainCharacterUser.lifePoints += (teamMember1User as CharacterWithMedicalSkills).medicalSkill

    if (attackTeamMember2User == "Heilung")
        mainCharacterUser.lifePoints += (teamMember2User as CharacterWithMedicalSkills).medicalSkill

    attackTeamPrint(teamMember1User, attackTeamMember1User, teamMember2User, attackTeamMember2User)

    lostLifePointsTeamPlay(attackTeamMember1User, selectionComputer, teamMember1User, mainCharacterComputer)
    lostLifePointsTeamPlay(attackTeamMember2User, selectionComputer, teamMember2User, mainCharacterComputer)
}

fun randomAttackTeamComputer() {

    var attackListTeamMember1 = mutableListOf<String>()
    var attackListTeamMember2 = mutableListOf<String>()

    var counter = 0

    for (character in teamComputer) {
        if (counter == 0) {
            teamMember1Computer = character
        } else {
            teamMember2Computer = character
        }
        counter = 1
    }

    if (teamMember1Computer is CharacterWithGenjutsu) {
        if (teamMember1Computer is CharacterWithGenjutsuAndSusanoo) {
            for (attack in teamMember1Computer.taijutsu)
                attackListTeamMember1.add(attack.key)

            for (attack in teamMember1Computer.ninjutsu)
                attackListTeamMember1.add(attack.key)

            for (attack in teamMember1Computer.weapon)
                attackListTeamMember1.add(attack.key)

        } else {
            for (attack in teamMember1Computer.taijutsu)
                attackListTeamMember1.add(attack.key)

            for (attack in teamMember1Computer.ninjutsu)
                attackListTeamMember1.add(attack.key)

            for (attack in teamMember1Computer.weapon)
                attackListTeamMember1.add(attack.key)
        }
    } else if (teamMember1Computer is CharacterWithMedicalSkills) {
        for (attack in teamMember1Computer.taijutsu)
            attackListTeamMember1.add(attack.key)

        for (attack in teamMember1Computer.ninjutsu)
            attackListTeamMember1.add(attack.key)

        for (attack in teamMember1Computer.weapon)
            attackListTeamMember1.add(attack.key)

        attackListTeamMember1.add("Heilung")

    } else if (teamMember1Computer is CharacterWithBijuu) {
        for (attack in teamMember1Computer.taijutsu)
            attackListTeamMember1.add(attack.key)

        for (attack in teamMember1Computer.ninjutsu)
            attackListTeamMember1.add(attack.key)

        for (attack in teamMember1Computer.weapon)
            attackListTeamMember1.add(attack.key)

    } else {
        for (attack in teamMember1Computer.taijutsu)
            attackListTeamMember1.add(attack.key)

        for (attack in teamMember1Computer.ninjutsu)
            attackListTeamMember1.add(attack.key)

        for (attack in teamMember1Computer.weapon)
            attackListTeamMember1.add(attack.key)
    }


    if (teamMember2Computer is CharacterWithGenjutsu) {
        if (teamMember2Computer is CharacterWithGenjutsuAndSusanoo) {
            for (attack in teamMember2Computer.taijutsu)
                attackListTeamMember2.add(attack.key)

            for (attack in teamMember2Computer.ninjutsu)
                attackListTeamMember2.add(attack.key)

            for (attack in teamMember2Computer.weapon)
                attackListTeamMember2.add(attack.key)

        } else {
            for (attack in teamMember2Computer.taijutsu)
                attackListTeamMember2.add(attack.key)

            for (attack in teamMember2Computer.ninjutsu)
                attackListTeamMember2.add(attack.key)

            for (attack in teamMember2Computer.weapon)
                attackListTeamMember2.add(attack.key)
        }
    } else if (teamMember2Computer is CharacterWithMedicalSkills) {
        for (attack in teamMember2Computer.taijutsu)
            attackListTeamMember2.add(attack.key)

        for (attack in teamMember2Computer.ninjutsu)
            attackListTeamMember2.add(attack.key)

        for (attack in teamMember2Computer.weapon)
            attackListTeamMember2.add(attack.key)

        attackListTeamMember2.add("Heilung")

    } else if (teamMember2Computer is CharacterWithBijuu) {
        for (attack in teamMember2Computer.taijutsu)
            attackListTeamMember2.add(attack.key)

        for (attack in teamMember2Computer.ninjutsu)
            attackListTeamMember2.add(attack.key)

        for (attack in teamMember2Computer.weapon)
            attackListTeamMember2.add(attack.key)

    } else {
        for (attack in teamMember2Computer.taijutsu)
            attackListTeamMember2.add(attack.key)

        for (attack in teamMember2Computer.ninjutsu)
            attackListTeamMember2.add(attack.key)

        for (attack in teamMember2Computer.weapon)
            attackListTeamMember2.add(attack.key)
    }

    attackTeamMember1Computer = attackListTeamMember1.random()
    attackTeamMember2Computer = attackListTeamMember2.random()

    if (attackTeamMember1Computer == "Heilung")
        mainCharacterComputer.lifePoints += (teamMember1Computer as CharacterWithMedicalSkills).medicalSkill

    if (attackTeamMember2Computer == "Heilung")
        mainCharacterComputer.lifePoints += (teamMember2Computer as CharacterWithMedicalSkills).medicalSkill

    attackTeamPrint(teamMember1Computer, attackTeamMember1Computer, teamMember2Computer, attackTeamMember2Computer)

    lostLifePointsSinglePlay(attackTeamMember1Computer, selectionUserString, teamMember1Computer, mainCharacterUser)
    lostLifePointsSinglePlay(attackTeamMember2Computer, selectionUserString, teamMember2Computer, mainCharacterUser)
}

// wenn die Auswahl vom Coputer Heilung ist, ruft er die Funktion randomAttackTeamComputer auf
fun attackComputerTeam () {

    if (selectionComputer == "Hilfe des Teams")
        randomAttackTeamComputer()
}

// der Funktion werden 4 Parameter mitgegeben, die Attacken der Spieler und die jeweiligen Charaktere,
// wenn die Spieler sich nicht für das Ausweichen entscheiden, werden die Lebenspunkte des Gegners um den Wert der Attacke verringert
// zum Schluss werden die Lebenspunkte der jeweiligen Spieler in einer Variablen außerhalb der Main gespeichert
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

// diese Funktion nimmt die Eingaben vom Typ String und sucht sie in der Charakterliste und
// speichert den Charakter vom Typ Charakters.Character, Charakters.CharacterWithGenjutsu oder Charakters.CharacterWithMedicalSkills in der Variablen mainCharacter
fun setCharacterForTeam(string: String) {

    for (character in characterList) {
        if (character is CharacterWithMedicalSkills) {
            if (character.name.lowercase() == string) {
                mainCharacterUser = character as CharacterWithMedicalSkills
            }
        } else if (character is CharacterWithGenjutsu) {
            if (character.name.lowercase() == string) {
                mainCharacterUser = character as CharacterWithGenjutsu
            }
            if (character is CharacterWithGenjutsuAndSusanoo) {
                if (character.name.lowercase() == string) {
                    mainCharacterUser = character as CharacterWithGenjutsuAndSusanoo
                }
            }
        } else if (character is CharacterWithBijuu) {
            if (character.name.lowercase() == string) {
                mainCharacterUser = character as CharacterWithBijuu
            }
        } else if (character is CharacterWithMoreStrength) {
            if (character.name.lowercase() == string) {
                mainCharacterUser = character as CharacterWithMoreStrength
            }
        } else {
            if (character.name.lowercase() == string) {
                mainCharacterUser = character
            }
        }
    }
}

// gibt einen Text aus, welches Teammitglied mit welcher Attacke geholfen hat
fun attackTeamPrint(member1: Character, attackMember1: String, member2: Character, attackMember2: String) {

    if (member1 == teamMember1User)
        println("\n${member1.name} hat dir mit $attackMember1 geholfen und \n${member2.name} hat dir mit $attackMember2 geholfen.")
    else
        println("\n${member1.name} und ${member2.name} haben dich mit angegriffen mit $attackMember1 und $attackMember2.")
}