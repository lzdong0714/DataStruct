package headfirst.a.unicorn.chain.actions;

import headfirst.a.unicorn.chain.Proxy.ActionChain;
import headfirst.a.unicorn.chain.Proxy.GenericAbstractAction;

import javax.security.sasl.AuthenticationException;
import java.io.IOException;

public class AuthenticationAction extends GenericAbstractAction {

    @Override
    public void doAction(Object input, Object output, ActionChain actionChain) throws IOException {

        String msg = (String)input;
        boolean isAuthenticated = msg.contains("unicorn");

        if(!isAuthenticated)
            throw new AuthenticationException("is not authentication");

        actionChain.doAction(input, output);
    }
}