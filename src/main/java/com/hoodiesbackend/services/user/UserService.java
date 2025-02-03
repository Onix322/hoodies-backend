package com.hoodiesbackend.services.user;

import com.hoodiesbackend.entities.cart.Cart;
import com.hoodiesbackend.entities.user.helpers.ActivationStatus;
import com.hoodiesbackend.entities.user.User;
import com.hoodiesbackend.entities.user.helpers.UserGetDto;
import com.hoodiesbackend.entities.user.helpers.UserMapper;
import com.hoodiesbackend.entities.user.helpers.LogIn;
import com.hoodiesbackend.exceptions.BadRequestException;
import com.hoodiesbackend.exceptions.NotFoundException;
import com.hoodiesbackend.exceptions.PasswordException;
import com.hoodiesbackend.repositories.UserRepository;
import com.hoodiesbackend.services.cart.CartService;
import com.hoodiesbackend.services.order.OrderService;
import com.hoodiesbackend.services.token.TokenService;
import jakarta.transaction.Transactional;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final CartService cartService;
    private final OrderService orderService;
    private final TokenService tokenService;

    public UserService(UserRepository userRepository, CartService cartService, OrderService orderService, TokenService tokenService) {
        this.userRepository = userRepository;
        this.cartService = cartService;
        this.orderService = orderService;
        this.tokenService = tokenService;
    }

    public User create(User entity) {

        String password = entity.getPassword();
        String confirmPassword = entity.getConfirmPassword();

        if (password.isBlank() || password.isEmpty()) {
            throw new PasswordException("Password of Confirm password id blank. You must fill them!");
        }
        if (!password.equals(confirmPassword)) {
            throw new PasswordException("Passwords don't match! Try again");
        }

        entity.setPassword(BCrypt.hashpw(entity.getPassword(), BCrypt.gensalt()));

        entity.setId(null);
        User user = userRepository.save(entity);

        Cart cart = new Cart();
        cart.setUser(user);
        cartService.create(cart);

        return user;
    }

    public UserGetDto read(Long id) {
        if (id < 0) {
            throw new BadRequestException("Id invalid!");
        }

        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found!"));

        return UserMapper.toUserGetDto(user);
    }

    public UserGetDto login(LogIn body) {

        if (!body.isValid()) {
            throw new BadRequestException("Email or password wrong!");
        }

        User user = userRepository.readUserByEmail(body.getEmail())
                .orElseThrow(() -> new NotFoundException("This user doesn't exist!"));

        if (user.getActivationStatus() == ActivationStatus.ACTIVATED &&
                BCrypt.checkpw(body.getPassword(), user.getPassword())) {

            tokenService.decode(tokenService.create(UserMapper.toUserGetDto(user)));
            return UserMapper.toUserGetDto(user);
        }

        throw new BadRequestException("User deactivated!");

    }

    public UserGetDto update(UserGetDto entity) {

        User userFound = userRepository.findById(entity.getId())
                .orElseThrow(() -> new NotFoundException("User not found!"));

        User userChanged = UserMapper.toUserWith(entity, userFound);


        return UserMapper.toUserGetDto(userRepository.save(userChanged));
    }

    @Transactional
    public void delete(Long id) {
        if (id <= 0) {
            throw new BadRequestException("Id is invalid!");
        }

        orderService.deleteAllByUserId(id);
        cartService.delete(id);
        userRepository.deleteById(id);
    }

    public Integer deactivate(Long id) {
        if (id <= 0) {
            throw new BadRequestException("Id is invalid!");
        }

        return userRepository.setActivationStatus(id, ActivationStatus.DEACTIVATED);
    }

    public Integer activate(Long id) {
        if (id <= 0) {
            throw new BadRequestException("Id is invalid!");
        }

        return userRepository.setActivationStatus(id, ActivationStatus.ACTIVATED);
    }
}
