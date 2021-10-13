const userTokenFromStorage = localStorage.getItem('token')
  ? JSON.parse(localStorage.getItem('token'))
  : null;

export const initialState = {
  token: userTokenFromStorage,
};

export const reducer = async (state, action) => {
  switch (action.type) {
    case 'GET':
      return initialState;
    case 'LOGIN':
      console.log(action);

      await localStorage.setItem('token', JSON.stringify(action.payload.token));

      return {
        ...state,
        token: action.payload.token,
      };
    case 'LOGOUT':
      localStorage.clear();
      return {
        ...state,
        token: null,
      };
    default:
      return state;
  }
};
