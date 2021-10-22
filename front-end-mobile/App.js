import React from 'react';
import { StyleSheet, Text, View, Button } from 'react-native';
import { NavigationContainer } from '@react-navigation/native';
import { createBottomTabNavigator } from '@react-navigation/bottom-tabs';

import HomeScreen from './screens/HomeScreen';
import MyProfileScreen from './screens/MyProfileScreen';
import ProjectsScreen from "./screens/ProjectsScreen";
import ScheduleScreen from "./screens/ScheduleScreen";
import TasksScreen from "./screens/TasksScreen";
import { FontAwesomeIcon } from '@fortawesome/react-native-fontawesome'
import { faCalendarAlt, faClipboardCheck, faHome, faProjectDiagram, faTasks, faUser } from '@fortawesome/free-solid-svg-icons'

// import NavBar from './components/NavBar';

export default function App() {

  const Tab = createBottomTabNavigator();

  return (
    <>
      <NavigationContainer>
        <Tab.Navigator
          screenOptions={{
            tabBarStyle: { position: 'absolute', backgroundColor: 'white', paddingBottom: 50, paddingTop: 30 },
          }}
        >
          <Tab.Screen
            tabBarIcon={faHome}
            name="Home"
            component={HomeScreen}
            tabB
            options={{
              tabBarLabel: '',
              tabBarIcon: ({ color }) => (
                <FontAwesomeIcon icon={faHome} size={20} color={color} />
              ),
            }}
          ></Tab.Screen>
          <Tab.Screen name="Profile" component={MyProfileScreen}
            options={{
              tabBarLabel: '',
              tabBarIcon: ({ color }) => (
                <FontAwesomeIcon icon={faUser} size={20} color={color} />

              ),
            }}
          ></Tab.Screen>
          <Tab.Screen name="Projects" component={ProjectsScreen}
            options={{
              tabBarLabel: '',
              tabBarIcon: ({ color }) => (
                <FontAwesomeIcon icon={faProjectDiagram} size={20} color={color} />

              ),
            }}
          ></Tab.Screen>
          <Tab.Screen name="Schedule" component={ScheduleScreen}
            options={{
              tabBarLabel: '',
              tabBarIcon: ({ color }) => (
                <FontAwesomeIcon icon={faCalendarAlt} size={20} color={color} />

              ),
            }}
          ></Tab.Screen>
          <Tab.Screen name="Tasks" component={TasksScreen}
            options={{
              tabBarLabel: '',
              tabBarIcon: ({ color }) => (
                <FontAwesomeIcon icon={faClipboardCheck} size={20} color={color} />

              ),
            }}
          ></Tab.Screen>
        </Tab.Navigator>
      </NavigationContainer>
    </>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#fff',
    alignItems: 'center',
    justifyContent: 'center',
  },
  navigation: {
    backgroundColor: "red"
  }
});
