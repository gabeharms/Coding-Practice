import gql from 'graphql-tag';

const LOGIN_MUTATION = gql`
  mutation LoginMutation($input: LoginUserInput!) {
    login(input: $input) {
      user {
        email
        id
      }
      token
    }
  }
`;

export { LOGIN_MUTATION };
