import { fireEvent, render, screen, waitFor } from '@testing-library/react';
import '@testing-library/jest-dom/extend-expect'
import each from 'jest-each';

import Login, { LoginResult } from './Login';

import { TestData } from './testServer';

describe('Login', () => {
    each([
        [ { username: TestData.USERNAME, password: TestData.PASSWORD }, LoginResult.SUCCESS ],
        [ { username: 'invalid', password: 'invalid' }, LoginResult.FAILURE ],
    ]).it('when the credentials are %s', async (credentials, result) => {
        let handleLoginResult = jest.fn();

        render(<Login onLoginResult={handleLoginResult}/>);

        for (let field in credentials) {
            let el = screen.getByTestId(field);
            fireEvent.change(el, { target: { value: credentials[field] } });
        }

        fireEvent.click(screen.queryByTestId('submit'));

        await waitFor(() => expect(handleLoginResult.mock.calls).toEqual([[result]]));
    })
});
