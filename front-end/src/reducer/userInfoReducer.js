const userInfoFromStorage = localStorage.getItem('user')
  ? JSON.parse(localStorage.getItem('user'))
  : null;
const userTokenFromStorage = localStorage.getItem('token')
  ? JSON.parse(localStorage.getItem('token'))
  : null;

export const initialState = {
  isAuthenticated: !userInfoFromStorage ? false : true,
  token: userTokenFromStorage,
  userInfo: userInfoFromStorage,
};

export const reducer = async (state, action) => {
  switch (action.type) {
    case 'GET':
      return initialState;
    case 'LOGIN':
      console.log(action);

      await localStorage.setItem('user', JSON.stringify(action.payload.user));
      await localStorage.setItem('token', JSON.stringify(action.payload.token));

      return {
        ...state,
        authentication: true,
        user: action.payload.user,
        token: action.payload.token,
      };
    case 'LOGOUT':
      localStorage.clear();
      return {
        ...state,
        isAuthenticated: false,
        token: null,
        user: {},
      };
    default:
      return state;
  }
};
