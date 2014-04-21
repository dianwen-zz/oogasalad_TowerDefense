package main.java.author.controller.tabbed_controllers;

import java.util.List;

import main.java.author.controller.MainController;
import main.java.author.controller.TabController;
import main.java.author.view.tabs.EditorTab;
import main.java.author.view.tabs.enemy.EnemyEditorTab;
import main.java.schema.tdobjects.MonsterSchema;
import main.java.schema.tdobjects.monsters.SimpleMonsterSchema;

public class EnemyController extends TabController {

	public EnemyController(MainController superController) {
		super(superController);
	}

	public void addEnemies(List<MonsterSchema> enemySchema) {

		mySuperController.addEnemiesToModel(enemySchema);

	}

	public String[] getEnemyNames() {
		EnemyEditorTab editorTab = (EnemyEditorTab) myEditorTab;
		return editorTab.getEnemyNamesArray();
	}
	
	public List<MonsterSchema> getMonsterSchemas() {
		EnemyEditorTab editorTab = (EnemyEditorTab) myEditorTab;
		return editorTab.getMonsterSchemas();
	}

	

	
}
