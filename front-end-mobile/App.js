import React from 'react';
import { StyleSheet, Text, View, Button } from 'react-native';
import { NavigationContainer } from '@react-navigation/native';
import { createBottomTabNavigator } from '@react-navigation/bottom-tabs';

import HomeScreen from './screens/HomeScreen';
import MyProfileScreen from './screens/MyProfileScreen';
import ProjectsScreen from "./screens/ProjectsScreen";
import ScheduleScreen from "./screens/ScheduleScreen";
import TasksScreen from "./screens/TasksScreen";

// import NavBar from './components/NavBar';

export default function App() {

  const Tab = createBottomTabNavigator();

  return (
    <>
      <NavigationContainer>
        <Tab.Navigator>
          <Tab.Screen name="Home" component={HomeScreen}></Tab.Screen>
          <Tab.Screen name="Profile" component={MyProfileScreen}></Tab.Screen>
          <Tab.Screen name="Projects" component={ProjectsScreen}></Tab.Screen>
          <Tab.Screen name="Schedule" component={ScheduleScreen}></Tab.Screen>
          <Tab.Screen name="Tasks" component={TasksScreen}></Tab.Screen>
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
});
