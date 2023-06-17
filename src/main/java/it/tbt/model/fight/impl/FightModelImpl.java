package it.tbt.model.fight.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import it.tbt.model.entities.characters.Character;
import it.tbt.model.entities.characters.Ally;
import it.tbt.model.entities.characters.CharacterFactory;
import it.tbt.model.entities.characters.Enemy;
import it.tbt.model.entities.characters.Status;
import it.tbt.model.entities.characters.skills.Skill;
import it.tbt.model.entities.items.Potion;
import it.tbt.model.entities.items.Antidote;
import it.tbt.model.entities.items.Item;
import it.tbt.model.fight.api.FightModel;
import it.tbt.model.party.IParty;
import it.tbt.model.statechange.StateObserver;

public class FightModelImpl implements FightModel {
    private StateObserver stateObserver;
    private List<Ally> allies;
    private List<Enemy> enemies;
    private List<Character> turnOrder;
    private Ally currentAlly;
    private Character currentCharacter;
    private IParty party;
    private int selectedTargetIndex;
    private boolean usingSkill;
    private boolean usingPotion;
    private boolean usingAntidote;

    public FightModelImpl(int averageEnemyStat) {
        this.enemies = new ArrayList<>();
        for (int c = 1; c < 5; c++) {
            this.addEnemy(CharacterFactory.createEnemy("Enemy " + c,
                    10, 1, 2));
        }
        this.selectedTargetIndex = 0;
        this.usingSkill = false;
        this.usingPotion = false;
        this.usingAntidote = false;
    }

    public FightModelImpl(IParty party, int averageEnemyStat) {
        this(averageEnemyStat);
        initializeParty(party);
    }

    @Override
    public void initializeParty(IParty party) {
        this.party = party;
        List<Ally> tmpAllies = new ArrayList<>(party.getMembers());
        this.allies = new ArrayList<>();
        int limit = Math.min(4, tmpAllies.size());
        for (int i = 0; i < limit; i++) {
            if (tmpAllies.get(i) != null) {
                party.getMembers().get(i).setHealth(party.getMembers().get(i).getMaxHealth());
                this.allies.add(party.getMembers().get(i));
            }
        }
        if (limit < 4) {
            for (int i = limit; i < 4; i++) {
                this.allies.add(CharacterFactory.createAlly("", 0, 0, 0));
            }
        }
        this.turnOrder = new ArrayList<>();
        this.turnOrder.addAll(allies);
        this.turnOrder.addAll(enemies);
        // ordina il turno in base alla speed, in modo decrescente
        Collections.sort(this.turnOrder, new Comparator<Character>() {

            @Override
            public int compare(Character arg0, Character arg1) {
                return arg1.getSpeed() - arg0.getSpeed();
            }

        });

        this.currentCharacter = this.turnOrder.get(0);
        if (this.currentCharacter instanceof Ally) {
            this.currentAlly = (Ally) currentCharacter;
        } else {
            enemyAttack();
        }

        party.addItemToInventory(new Potion("Potion", 3, 10));
        party.addItemToInventory(new Potion("Potion", 3, 10));
        party.addItemToInventory(new Potion("Potion", 3, 10));
        party.addItemToInventory(new Antidote(5));
        // System.out.println(party.getInventory().isEmpty());
    }

    public void addEnemy(Enemy enemy) {
        this.enemies.add(enemy);
    }

    public List<Ally> getAllies() {
        return allies;
    }

    public List<Enemy> getEnemies() {
        return enemies;
    }

    public Ally getCurrentAlly() {
        return this.currentAlly;
    }

    public Ally getSelectedAlly() {
        return this.allies.get(selectedTargetIndex);
    }

    public Enemy getSelectedEnemy() {
        return this.enemies.get(selectedTargetIndex);
    }

    @Override
    public int getSelectedTargetIndex() {
        return this.selectedTargetIndex;
    }

    public boolean isUsingSkill() {
        return this.usingSkill;
    }

    public boolean isUsingPotion() {
        return this.usingPotion;
    }

    public boolean isUsingAntidote() {
        return this.usingAntidote;
    }

    public void selectPreviousTarget() {
        int previousTarget = Math.max(0, selectedTargetIndex - 1);
        if ((isUsingSkill() || (!isUsingAntidote() && !isUsingPotion() && !isUsingSkill()))
                && getEnemies().get(previousTarget).getMaxHealth() != 0) {
            this.selectedTargetIndex = previousTarget;
        } else if ((isUsingAntidote() || isUsingPotion()) && getAllies().get(previousTarget).getMaxHealth() != 0) {
            this.selectedTargetIndex = previousTarget;
        }
    }

    public void selectNextTarget() {
        int nextTarget = Math.min(enemies.size() - 1, selectedTargetIndex + 1);
        if ((isUsingSkill() || (!isUsingAntidote() && !isUsingPotion() && !isUsingSkill()))
                && getEnemies().get(nextTarget).getMaxHealth() != 0) {
            this.selectedTargetIndex = nextTarget;
        } else if ((isUsingAntidote() || isUsingPotion()) && getAllies().get(nextTarget).getMaxHealth() != 0) {
            this.selectedTargetIndex = nextTarget;
        }
    }

    public void selectAction(boolean changeUsingSkillState, boolean changeUsingPotionState,
            boolean changeUsingAntidoteState) {
        selectedTargetIndex = 0;
        this.usingSkill = changeUsingSkillState;
        this.usingPotion = changeUsingPotionState;
        this.usingAntidote = changeUsingAntidoteState;
    }

    public void performSelectedAction() {
        Character character = (Character) getCurrentAlly();
        Character selectedAlly = (Character) getSelectedAlly();
        Character target = (Enemy) getSelectedEnemy();

        if (usingSkill && currentAlly.getSkills() != null && currentAlly.getSkills().size() != 0
                && currentAlly.getSkills().get(0) != null && target.getHealth() != 0) {
            Skill skill = currentAlly.getSkills().get(0); // prende la skill equipaggiata (quella in posizione 0)
            if (skill.getRemainingCooldown() == 0) {
                double damage = character.getAttack() * skill.getAttackMultiplier();
                Random crit = new Random();
                Double critProb = crit.nextDouble();
                if (critProb <= 0.05 || (skill.isIncProbCritical() && critProb <= 0.25)) {
                    damage *= 2.0;
                    System.out.println("Critical Hit!");
                }
                target.takeDamage((int) damage);
                if (skill.getPossibleStatus().get() == Status.POISONED) {
                    target.addStatus(Status.POISONED);
                }
                skill.resetCooldown();
                usingSkill = false;
            } else {
                return;
            }
        } else if (usingSkill) { // se il player sta provando ad usare una skill mentre è in cooldown
            return;
        } else if (usingPotion) { // codice che gestisce l'uso di pozioni

            if (selectedAlly.getMaxHealth() == selectedAlly.getHealth()) { // se il personaggio non può essere curato
                                                                           // esce
                return;
            }

            List<Potion> potions = new ArrayList<>();
            for (Map.Entry<Item, Integer> item : party.getInventory().entrySet()) { // si tira giù tutte le pozioni
                Item key = item.getKey();
                if (key instanceof Potion && item.getValue() > 0) {
                    potions.add((Potion) key);
                }
            }

            if (potions.isEmpty()) {
                return; // se non ha pozioni non fa nulla
            }

            int missingHealth = selectedAlly.getMaxHealth() - selectedAlly.getHealth();
            Potion currenPotion = null;
            for (Potion p : potions) {
                if (currenPotion == null) {
                    currenPotion = p;
                } else if (currenPotion.getHealPower() > p.getHealPower() && p.getHealPower() > missingHealth
                        && currenPotion.getHealPower() > missingHealth) {
                    // usa la pozione che "sprecherebbe" meno hp
                    currenPotion = p;
                } else if (p.getHealPower() < missingHealth && currenPotion.getHealPower() > missingHealth) {
                    // tende ad usare pozioni che non eccedono il massimo degli hp
                    currenPotion = p;
                } else if (p.getHealPower() < missingHealth && currenPotion.getHealPower() < missingHealth
                        && currenPotion.getHealPower() < p.getHealPower()) {
                    // se la pozione checkata cura di più di quella corrente E non supera gli hp
                    // massimi tende ad usarla
                    currenPotion = p;
                }
            }

            if (currenPotion == null) {
                return;
            }

            int healAmount = selectedAlly.getHealth() + currenPotion.getHealPower();
            int scartoHeal = healAmount - selectedAlly.getMaxHealth();
            if (scartoHeal > 0) {
                healAmount -= scartoHeal;
            }
            selectedAlly.setHealth(healAmount); // Cura l'alleato, non superando gli hp massimi

            party.removeItemFromInventory(currenPotion);

            System.out.println(potions.size());

            usingPotion = false;
        } else if (usingAntidote) { // codice che gestisce l'uso di antidoti
            Antidote antidote = null;
            for (Map.Entry<Item, Integer> item : party.getInventory().entrySet()) {
                Item key = item.getKey();
                if (key instanceof Antidote && item.getValue() > 0) {
                    antidote = (Antidote) key;
                }
            }

            if (antidote == null || !selectedAlly.getStatuses().contains(Status.POISONED)) {
                System.out.println("O non è avvelenato o non hai antidoti");
                return; // se non ha antitodi non fa nulla
            }

            selectedAlly.removeStatus(Status.POISONED);
            party.removeItemFromInventory(antidote); // rimuove l'antidoto dall'inventario
            System.out.println("Hai usato un antidoto");
            usingAntidote = false;
        } else if (!usingSkill && !usingAntidote && !usingPotion && target.getHealth() != 0) { // codice che gestisce
                                                                                               // l'attacco normale
            double damage = character.getAttack();
            Random crit = new Random();
            Double critProb = crit.nextDouble();
            if (critProb <= 0.05) {
                damage *= 2.0;
                System.out.println("Critical Hit!");
            }
            target.takeDamage((int) damage);
        } else if (target.getHealth() == 0) {
            System.out.println("Stai attaccando un nemico con 0 hp");
            return;
        } else {
            System.out.println("???");
            return;
        }

        if (!checkBattleEnd()) {
            advanceTurn();
        }
    }

    private void decreaseCooldowns() {
        for (Ally ally : allies) {
            if (ally.getSkills() != null) {
                if (ally.getSkills().size() >= 1) {
                    Skill skill = ally.getSkills().get(0);
                    skill.decreaseCooldown();
                }
            }
        }
    }

    private void applyPoisonDamage() {
        for (Enemy enemy : enemies) {
            if (enemy.getStatuses().contains(Status.POISONED)) {
                double poisonDamage = (enemy.getMaxHealth() * 0.05) + 1;
                enemy.takeDamage((int) poisonDamage);
            }
        }
        for (Ally ally : allies) {
            if (ally.getStatuses().contains(Status.POISONED)) {
                double poisonDamage = (ally.getMaxHealth() * 0.05) + 1;
                ally.takeDamage((int) poisonDamage);
            }
        }
    }

    public void enemyAttack() {
        if (checkBattleEnd()) {
            return;
        }
        Random random = new Random();
        Enemy currentEnemy = (Enemy) this.currentCharacter;
        int currentTarget = random.nextInt(0, 4);
        while (this.allies.get(currentTarget).getHealth() == 0) {
            currentTarget = random.nextInt(0, 4);
        }
        double damage = currentEnemy.getAttack();
        Double critProb = random.nextDouble();
        if (critProb <= 0.05) {
            damage *= 2.0;
            System.out.println("Critical Hit!");
        }
        this.allies.get(currentTarget).takeDamage((int) damage);
        Double poisonProb = random.nextDouble();
        if (poisonProb <= 0.03) {
            this.allies.get(currentTarget).addStatus(Status.POISONED);
            System.out.println(this.allies.get(currentTarget).getName() + " è stato avvelenato!");
        }
        if (!checkBattleEnd()) {
            advanceTurn();
        }
    }

    public void advanceTurn() {
        if (checkBattleEnd()) {
            return;
        }
        int currentIndex = turnOrder.indexOf(this.currentCharacter);
        int nextIndex = (currentIndex + 1) % this.turnOrder.size();
        while (this.turnOrder.get(nextIndex).getHealth() == 0) {
            nextIndex++;
            nextIndex = nextIndex % this.turnOrder.size();
        }
        this.currentCharacter = this.turnOrder.get(nextIndex);
        if ((nextIndex) % this.turnOrder.size() == 0) {
            decreaseCooldowns();
            applyPoisonDamage();
            System.out.println("cooldown skill diminuito e danno da veleno applicato");
            if (checkBattleEnd()) {
                return;
            }
        }
        if (this.currentCharacter instanceof Ally) {
            this.currentAlly = (Ally) currentCharacter;
        } else {
            enemyAttack();
        }
    }

    private boolean checkBattleEnd() {
        boolean allAlliesDefeated = true;
        boolean allEnemiesDefeated = true;
        for (Ally ally : allies) {
            if (ally.getHealth() > 0) {
                allAlliesDefeated = false;
                break;
            }
        }
        for (Enemy enemy : enemies) {
            if (enemy.getHealth() > 0) {
                allEnemiesDefeated = false;
                break;
            }
        }
        if (allAlliesDefeated) {
            System.out.println("Enemies win!");
            this.stateObserver.onExplore();
        } else if (allEnemiesDefeated) {
            System.out.println("Allies win!");
            this.stateObserver.onExplore();
        }
        return (allAlliesDefeated || allEnemiesDefeated);
    }

    @Override
    public void setStateObserver(StateObserver stateObserver) {
        this.stateObserver = stateObserver;
    }

}
