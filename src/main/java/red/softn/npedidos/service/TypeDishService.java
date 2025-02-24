package red.softn.npedidos.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import red.softn.npedidos.entity.FoodDish;
import red.softn.npedidos.entity.TypeDish;
import red.softn.npedidos.repository.FoodDishRepository;
import red.softn.npedidos.repository.TypeDishRepository;
import red.softn.npedidos.request.FoodDishRequest;
import red.softn.npedidos.request.TypeDishRequest;
import red.softn.npedidos.response.FoodDishResponse;
import red.softn.npedidos.response.TypeDishResponse;

import java.util.List;

@Service
@RequiredArgsConstructor
@Getter
public class TypeDishService extends CrudService<TypeDishRequest, TypeDishResponse, TypeDish, Integer> {
    
    private final TypeDishRepository repository;
    
    private final FoodDishRepository foodDishRepository;
    
    public FoodDishResponse save(Integer id, FoodDishRequest request) {
        FoodDish foodDish = getGsonUtil().convertTo(request, FoodDish.class);
        TypeDish typeDish = getRepository().getReferenceById(id);
        
        typeDish.setFoodDishes(List.of(foodDish));
        foodDish.setTypeDish(typeDish);
        
        FoodDish save = foodDishRepository.save(foodDish);
        
        return getGsonUtil().convertTo(save, FoodDishResponse.class);
    }
    
    public List<FoodDishResponse> findAllFoodDishes(Integer id) {
        List<FoodDish> allByTypesDishesId = foodDishRepository.findAllByTypeDishId(id);
        
        return getGsonUtil().convertTo(allByTypesDishesId, FoodDishResponse.class);
    }
    
    public FoodDishResponse findByIdFoodDish(Integer id, Integer foodDishId) {
        FoodDish byIdAndTypeDishId = foodDishRepository.findByIdAndTypeDishId(foodDishId, id);
        
        return getGsonUtil().convertTo(byIdAndTypeDishId, FoodDishResponse.class);
    }
    
}
