import React from 'react'
import { View, Text, Button } from 'react-native'
import HomeScreen from '../screens/HomeScreen';
import MyProfileScreen from '../screens/MyProfileScreen';
import ProjectsScreen from '../screens/ProjectsScreen';
import ScheduleScreen from '../screens/ScheduleScreen';
import TasksScreen from '../screens/TasksScreen';

const Tab = createBottomTabNavigator();


export default function NavBar({ navigation }) {
    return (
        <Tab.Navigator>
            <Tab.Screen name="HomeScreen" component={HomeScreen}></Tab.Screen>
            <Tab.Screen name="MyProfileScreen" component={MyProfileScreen}></Tab.Screen>
            <Tab.Screen name="ProjectsScreen" component={ProjectsScreen}></Tab.Screen>
            <Tab.Screen name="ScheduleScreen" component={ScheduleScreen}></Tab.Screen>
            <Tab.Screen name="TasksScreen" component={TasksScreen}></Tab.Screen>
        </Tab.Navigator>
    )
}
