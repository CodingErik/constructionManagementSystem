// import React from 'react';
// import {
//     Pressable,
//     StyleSheet,
//     Text,
//     useWindowDimensions,
//     View,
// } from 'react-native';


// const TaskItem = ({ todo: title, handleLongPress,  handleToggleCompletion }) => {
//     // We want our `Pressable` to be full width of the screen
//     const viewStyle = { ...styles.view, width: useWindowDimensions().width };

//     //! style 
//     const textStyle = {
//         textDecorationLine: title.completed ? 'line-through' : 'none',
//       };


//     return (
//         <View style={viewStyle}>
//             {/* `Pressable` takes in a callback for it's style to conditionally render when depending on if it's being pressed or not */}
//             <Pressable
//                 // when the long press happens here 
//                 onLongPress={() => handleLongPress(title.id)}
//                 onPress={() => { handleToggleCompletion(title.id) }}
//                 onPressOut={() => console.log('press out!')}
//                 // We apply multiple styles by using an array
//                 style={({ pressed }) => [
//                     // Conditional styling like in React
//                     {
//                         backgroundColor: pressed ? 'rgb(210, 230, 255)' : '#f8f8f8',
//                         backgroundColor: title.completed ? 'green': '#f8f8f8',
//                     },
//                     styles.pressable,
//                 ]}>
//                 {/* {!todo.completed ? (

//                     <Text>{todo.task + '   ' + todo.id}</Text>
//                 ) : (
//                     <Text style={styles.strikeout}>{todo.task + '   ' + todo.id}</Text>
//                 )} */}
//                 {/* !! their solution */}
//                 <Text style={textStyle}>{title.task + '   ' + title.id}</Text>
//             </Pressable>
//         </View>
//     );
// };

// const styles = StyleSheet.create({
//     pressable: {
//         borderWidth: StyleSheet.hairlineWidth,
//         marginVertical: 4,
//         padding: 16,
//     },
//     view: {
//         flex: 1,
//         justifyContent: 'center',
//     },
//     strikeout: {
//         textDecorationLine: "line-through"
//     }
// });

// export default TaskItem;