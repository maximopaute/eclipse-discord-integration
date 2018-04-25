package fr.kazejiyu.discord.rpc.integration.settings;

import static java.util.Objects.requireNonNull;
import static fr.kazejiyu.discord.rpc.integration.settings.Settings.*;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.ui.preferences.ScopedPreferenceStore;

/**
 * Provides easy access to the settings defined by the user for Discord Integration.
 * 
 * @author Emmanuel CHEBBI
 */
public class DiscordIntegrationPreferences {
	
	private static final String PREFERENCES_STORE_ID = "fr.kazejiyu.discord.rpc.integration.preferences.store";

	private List<SettingChangeListener> listeners = new ArrayList<>();
	
	private final IPropertyChangeListener listener;
	
	private final IPreferenceStore store;
	
	public DiscordIntegrationPreferences() {
		this.store = new ScopedPreferenceStore(InstanceScope.INSTANCE, PREFERENCES_STORE_ID);
		this.listener = new DiscordIntegrationPreferencesListener(listeners);
		
		this.store.addPropertyChangeListener(this.listener);
	}
	
	/** @return whether the name of the edited file should be shown in Discord */
	public boolean showsFileName() {
		return store.getBoolean(SHOW_FILE_NAME.property());
	}
	
	/** @return whether the name of the current project should be shown in Discord */
	public boolean showsProjectName() {
		return store.getBoolean(SHOW_PROJECT_NAME.property());
	}
	
	/** @return whether the time elapsed should be shown in Discord */
	public boolean showsElapsedTime() {
		return store.getBoolean(SHOW_ELAPSED_TIME.property());
	}
	
	/** @return whether the name of the current project should be shown in Discord */
	public boolean resetsElapsedTimeOnStartup() {
		return store.getString(RESET_ELAPSED_TIME.property()).equals(RESET_ELAPSED_TIME_ON_STARTUP.property());
	}
	
	/** @return whether the name of the current project should be shown in Discord */
	public boolean resetsElapsedTimeOnNewProject() {
		return store.getString(RESET_ELAPSED_TIME.property()).equals(RESET_ELAPSED_TIME_ON_NEW_PROJECT.property());
	}
	
	/** @return whether the name of the current project should be shown in Discord */
	public boolean resetsElapsedTimeOnNewFile() {
		return store.getString(RESET_ELAPSED_TIME.property()).equals(RESET_ELAPSED_TIME_ON_NEW_FILE.property());
	}

	/**
	 * Registers a new listener that will be called each time a property change.
	 * 
	 * @param listener
	 * 			The listener to register.
	 */
	public void addSettingChangeListener(SettingChangeListener listener) {
		listeners.add(requireNonNull(listener, "Cannot register a null listener"));
	}

	/**
	 * Unregisters a listener so that it will no longer being notified of events.
	 * 
	 * @param listener
	 * 			The listener to unregister.
	 */
	public void removeSettingChangeListener(SettingChangeListener listener) {
		listeners.remove(listener);
	}
}