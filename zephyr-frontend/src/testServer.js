import { rest } from 'msw';
import { setupServer } from 'msw/node';

const TestData = {
    USERNAME: 'Test username',
    PASSWORD: 'Test password',
};

const server = setupServer(
    rest.post(`/authentication`, (req, res, ctx) => {
        let response = req.body.username === TestData.USERNAME
                        && req.body.password === TestData.PASSWORD;

        return res(ctx.json({ response }));
    }),
);

export {
    TestData,
    server,
};
